### Spark的启动过程
1. 构建Spark Application的运行环境（启动SparkContext），SparkContext向资源管理器（可以是Standalone、Mesos或YARN）注册并申请运行Executor资源。
2. 资源管理器为Executor分配资源并启动Executor进程，Executor运行情况将随着“心跳”发送到资源管理器上。
3. SparkContext构建DAG图，将DAG图分解成多个Stage，并把每个Stage的TaskSet（任务集）发送给Task Scheduler (任务调度器）。
4. Executor向SparkContext申请Task， Task Scheduler将Task发放给Executor，同时，SparkContext将应用程序代码发放给Executor。
5. Task在Executor上运行，把执行结果反馈给Task Scheduler，然后再反馈给DAG Scheduler。
6. 当一个阶段完成后，Spark 会根据数据依赖关系将结果传输给下一个阶段，并开始执行下一个阶段的任务。
7. 最后，当所有阶段都完成后，Spark 会将最终结果返回给驱动程序，并完成作业的执行。

### 小文件问题怎么解决？
- 识别
  - 比如小于一个block（128M的文件）
- 危害
  - 任务执行时间长 
  - 真实的文件大小独占一个数据存储块，有效空间太少
  - 如果HDFS发生重启，将产生较长时间的元数据从硬盘读到内存的过程。
  - 不论在Hive还是在Spark中，每一个存储块都对应一个Map程序，一个Map呈现就需要一个JVM，启动一个JVM去读取或者写小文件是吃力不讨好的行为
  - 其元数据会占用大量 namenode内存（一个元数据大概150字节），影响namenode性能
  - 影响磁盘寻址时间
- 原因
  - 往动态分区表插入数据时，会插入大量小文件
  - reduce数量太多
  - 源数据文件就存在大量的小文件
- 合并
  - 打Har，合并为大文件
  - 设置参数（Hive作业）
    - mapred.max.split.size，每个Map最大输入大小；hive.merge.size.per.task，设置合并的大小
    - sethive.merge.mapredfiles= true
  - 往动态分区插入数据时，在已经写好的SQL末尾加上distribute by rand()，打散后再设置每个reduce任务的文件大小
  - repartition()
  - coalesce()
  - 降低spark并行度，即调节spark.sql.shuffle.partitions
  - 新增一个并行度=1任务，专门合并小文件
- 效果
  - 减少文件的元数据（文件名、权限等）

### 数据倾斜问题怎么解决？
- spark内部（在sortMergeJoin阶段生效），spark3.0的AQE（Adaptive Query Execution，自适应查询执行）
  - 倾斜分区的判定（且的关系）
    - 大于5*分区中位数的分区
    - 大于256M（参数可配）
  - 动作：逻辑拆分，拆分到最接近targetSize的规模
    - targetSize：取advisoryPartitionSizeInBytes（64M）和非倾斜分区平均值的较大值
    - 逻辑：advisoryPartitionSizeInBytes是为了控制小任务数量的合并分区阈值，避免倾斜分区导致的拆分引发小任务
- 外部
  - 解决方案一：使用 Hive ETL 预处理数据，提前group by，把数据倾斜的前提提前至ETL
    - 治标不治本，Hive ETL中还是会发生数据倾斜
  - 解决方案二：过滤少数导致倾斜的key，这些key单独处理
    - 适用场景不多，大多数情况下，导致倾斜的key很多
  - 解决方案三：提高shuffle操作的并行度
    - 缓解了数据倾斜，没有彻底解决
  - 解决方案四：两阶段聚合（局部聚合+全局聚合），第一阶段局部聚合，每个key打一个随机数，第二阶段去掉前缀
    - 仅适用于聚合类的shuffle操作，不适用是join类的shuffle操作
  - 解决方案五：将reduce join转为map join，使用Broadcast变量与map类算子实现join操作，进而完全规避掉shuffle类的操作
    - 适用于join操作中的一个RDD或表的数据量比较小，因为要把小表广播（广播变量在executor的所有task共享）
  - 解决方案六：采样倾斜key并分拆join操作，拆分左右两边的大key，打散后join
    - 适用于join两个大表的情况，且倾斜的key数量不多
  - 解决方案七：使用随机前缀和扩容RDD进行join，每条数据都打上前缀，数据膨胀N倍，对内存资源要求较高
    - 适用于join两个大表的情况，且倾斜的key数量很多（使用6意义不大）

### stage怎么划分？task怎么划分？
- stage主要是基于Shuffle；以Actions算子为起点，从后向前回溯DAG，以Shuffle操作为边界去划分Stages
- 每个Stage里task的数量由Stage最后一个RDD中的分区数决定
- task优先级：PROCESS_LOCAL（进程）, NODE_LOCAL（节点，分为磁盘或同节点不同进程）, NO_PREF（无本地化）, RACK_LOCAL（机架）, ANY （任意）的顺序
  - NO_PREF 排在 RACK_LOCAL 之前是因为在有些情况下，RACK_LOCAL 的获取成本可能比较高，
  - 例如网络负载较重、数据分布不均等情况下，此时获取 RACK_LOCAL 级别的数据需要进行网络传输，传输的成本较高，
  - 而获取 NO_PREF 级别的数据可以随机选择任何节点来获取，因此在这种情况下选择 NO_PREF 级别的本地化可能会更加高效。

### spark的分区
- 在Spark集群中每个工作节点，可能都包含一个或多个分区。
- Spark中使用的分区数是可配置的，但要注意，分区太少或分区太多都不好。
  - 分区太少，会导致较少的并发、数据倾斜、或不正确的资源利用。
  - 分区太多，导致任务调度花费比实际执行时间更多的时间。若没有配置分区数
  - 默认的分区数是：所有执行程序节点上的内核总数（defaultParallelism = totalCores）
- Spark保证同一个分区的数据位于同一个机器上，不会跨多台机器保存。
- Spark会为每个分区分配一个任务，有多少个分区就会在Executor端生成多少个任务。
- 分区器(Partitioner，一般有Hash和Range)
  - 定义了如何分布数据，决定一个RDD可以分成多少个分区，每个分区的数据量有多大，从而决定了每个Task将处理哪些数据。

### Spark和MR的区别
#### 内存计算
- 含义：第一层含义就是众所周知的分布式数据缓存（RDD cache），第二层含义是Stage内的流水线式计算模式
- 缓存：需要频繁访问的数据集才有必要cache
### 比较
- MR的问题：所有操作之间的数据交换都以磁盘为媒介，包括Map和Map之间、Map和Reduce之间，效率比较低
- spark的做法：在同一Stage内部，所有算子融合为一个函数，Stage的输出结果由这个函数一次性作用在输入数据集而产生
  - 注意：不是每个算子都cache一遍，下一个算子再根据结果计算，而是从不cache
- MR的map阶段
  - Map阶段：Shuffle writer按照Reducer的分区规则将中间数据写入本地磁盘
  - Reduce 阶段：Shuffle reader从各个节点下载数据分片，并根据需要进行聚合计算
  - 举例：有stage0 -> shuffle0 -> stage1，Shuffle 0来说，Stage 0是Map阶段，Stage 1是Reduce阶段；对后面的stage，Stage 1是Map阶段，Stage 2是Reduce阶段

### 堆内和堆外内存
- 堆内：内存的申请与释放统一由JVM代劳
  - Execution Memory：用于执行分布式任务，如Shuffle、Sort和Aggregate等操作
  - Storage Memory：用于缓存RDD和广播变量等数据
  - User Memory：开发者自定义数据结构
  - Reserved Memory（300M）：存储各种Spark内部对象，例如存储系统中的BlockManager、DiskBlockManager
  - Rdd cache的存储级别：从memory_only -> memory_disk_ser
- 堆外：调用Unsafe的allocateMemory和freeMemory方法直接在操作系统内存中申请、释放内存空间
  - 免去了JVM带来的频繁扫描和回收引入的性能开销
  - 内存计算的两层含义也就是数据集缓存和Stage内的流水线计算，对应着Storage 和 Execution memory
  - Execution Memory：用于执行分布式任务，如Shuffle、Sort和Aggregate等操作
  - Storage Memory：用于缓存RDD和广播变量等数据
- 统一内存管理（1.6）：二者可以相互转化，但Execution优先级更高（否则CPU就浪费了），
  - 如果对方的内存空间有空闲，双方就都可以抢占；
  - 对于RDD缓存任务抢占的执行内存，当执行任务有内存需要时，RDD缓存任务必须立即归还抢占的内存，涉及的RDD缓存数据要么落盘、要么清除；
  - 对于分布式计算任务抢占的Storage Memory内存空间，即便RDD缓存任务有收回内存的需要，也要等到任务执行完毕才能释放。

### AQE简介（Adaptive Query Execution，自适应查询执行，Spark SQL的动态优化机制，3.0版本）
- 自动分区合并（物理计划）
  - 自动检测过小（小于advisoryPartitionSizeInBytes，64M）的数据分区，并对它们自动合并
- 数据倾斜（物理计划）
  - 见数据倾斜问题怎么解决
- Join策略调整（物理计划 + 逻辑计划）
  - join的其中一个表过滤后，小到足可以由广播变量，则从Sort Merge Join变成Broadcast Join
### DPP（Dynamic Partition Pruning，动态分区剪裁）是3.0另一大特性
- 充分利用过滤之后的维度表，大幅削减事实表的数据扫描量，从整体上提升关联计算的执行性能
- 分区裁剪：
  - 谓词下推的一种特例，如果过滤谓词中包含分区键，那么Spark SQL对分区表做扫描的时候，是完全可以跳过（剪掉）不满足谓词条件的分区目录
  - 注意，这里只能针对过滤谓词中包含分区键，如果表本身是非分区表（Hive层面）或谓词中不包含分区键，则使用不了该特性
- 动态分区裁剪
  - 把维度表中的过滤条件，通过关联关系传导到事实表（大表），来完成事实表的优化
  - 条件
    - 事实表必须是分区表，而且分区键（可以是多个）必须包含Join key
    - DPP仅支持等值Joins，不支持大于、小于这种不等值关联关系。
    - 维度表过滤之后的数据集要小于广播阈值。因为传导的过程是基于广播

### stage和task的关系？job和task的关系？
- Application提交后，当Spark遇到一个Action算子（reduce、collect、show、count、foreach、saveAsTextFile）时，就会提交一个Job
  - 多个Job时
    - FIFO：第一个作业优先使用所有可用资源，后面作业没有可用资源时，要延迟启动
    - FAIR：“循环”方式在作业之间分配任务，从而使所有作业获得大致均等的集群资源份额
  - Job和Task的关系：一个Job通常由多个Task组成
  - Stage和Task的关系：
    - 一个Stage通常由多个Task组成。
    - 每个Task执行相同的程序逻辑，只是它们操作的数据不同。
    - 在RDD计算过程中，每个分区都会起一个Task，因此RDD的分区数目决定了总的Task数目，也决定了一个stage的并发度
  - Stage和Job的关系：
    - 一个Job通常包含一个或多个Stage。各个Stage之间按照顺序执行
    - 一个Stage的划分依据是RDD间的依赖关系。当遇到宽依赖（Wide Dependency）时，因需要进行Shuffle操作，这涉及到了不同Partition之间进行数据合并，故以此为界划分不同的Stage
- task的分类
  - ShuffleMapTask
  - ResultTask
    - DAG的最后一个阶段会为每个结果的partition生成一个ResultTask
    - 即每个Stage里面的Task的数量是由该Stage中最后一个RDD的Partition的数量所决定的
#### task的数量和并发
- rdd 的partition数量决定了task的数量，也决定了并发数
  - 可以在创建RDD时指定，也可以通过reparation（shuffle）和coalesce（不shuffle，只能减少）等算子重新进行划分

### RDD、DataFrame和DataSet的区别，及和RDD的区别？
- RDD的优化空间受限，因为Spark Core只知道“做什么”，而不知道“怎么做”
- 区别
  - DataFrame就是携带数据模式（Data Schema）的结构化分布式数据集，而RDD是不带Schema的分布式数据集。
    - DataFrame只能封装结构化数据，
    - RDD除了结构化数据，还可以封装半结构化和非结构化数据
  - RDD算子多是高阶函数，这些算子允许开发者灵活地实现业务逻辑，表达能力极强；DataFrame的表达能力却很弱。
    - DataFrame是基于列的（select、filter、agg、groupBy等）
  - DataFrame的优势（有逐步取代RDD的趋势，Spark SQL也逐渐取代Spark Core）
    - 首先，DataFrame中Schema所携带的类型信息，让Spark可以根据明确的字段类型设计定制化的数据结构，从而大幅提升数据的存储和访问效率。
    - 其次，DataFrame中标量算子确定的计算逻辑，让Spark可以基于启发式的规则和策略，甚至是动态的运行时信息去优化DataFrame的计算过程。
    - 可以用Catalyst优化器和Tungsten计划
      - Catalyst优化器
        - 逻辑优化阶段
          - 谓词下推（Predicate Pushdown）：在源头就减少数据扫描量
          - 列剪裁（Column Pruning）：扫描数据源的时候，只读取那些与查询相关的字段
          - 常量替换 （Constant Folding）
        - 物理计划
          - 优化Spark Plan：Join Select，优先选效率高的，如Broadcast Hash Join（BHJ）
          - 生成Physical Plan：ReuseSubquery、ReuseExchange、EnsureRequirements（多个算子捏合成一个算子）
      - Tungsten
        - 内存利用率更高
          - 二进制内存序列代替JVM对象的存储方式，提升CPU的存储效率，还能减少存储数据记录所需的对象个数，从而改善GC效率
          - 基于Tungsten内存地址和内存页，同时管理堆内和堆外地址
            - 堆内：前64位存储的是JVM堆内对象的引用或者说指针，后64位Offset存储的是数据在该对象内的偏移地址
            - 堆外：前64位存储的是null值，后64位则用于在堆外空间中直接寻址操作系统的内存空间
        - WSCG（WholeStageCodegen）：把多个RDD的compute函数捏合成一个，然后把这一个函数一次性地作用在输入数据上
          - 火山模型：1. 内存数据的随机存取，2. 是虚函数调用（next和hasNext），WSCG没有这两个问题
          - 通过手写代码，指令更明确，充分利用了CPU的寄存器和各级缓存，大幅提升了CPU的工作效率
- DataSet：提供了更好的类型安全性和更好的性能
  - Spark推出DataSet是为了提供一种类型安全的API，它结合了RDD的强类型和Spark SQL的优化执行引擎的优点。
  - DataFrame是DataSet的一个特例，DataFrame = DataSet[Row]
  - DataFrame与Dataset均支持sparksql的操作，比如select，groupby之类
  - DataFrame只是知道字段，但是不知道字段的类型，所以在执行这些操作的时候是没办法在编译的时候检查是否类型失败的，
    - 比如你可以对一个String进行减法操作，在执行的时候才报错，
    - DataSet不仅仅知道字段，而且知道字段类型，所以有更严格的错误检查。
    - 类似于JSON对象和类对象之间的类比。



### WholeStageCodegen是算子融合吗？
- 是的，参考Tungsten部分

### Spark OOM怎么办 
- 只有运行时区域，才有可能OOM
  - Reserved Memory大小固定为300MB，因为它是硬编码到源码中的，所以不受用户控制。
  - 对于Storage Memory来说，即便数据集不能完全缓存到MemoryStore，Spark也不会抛OOM异常，额外的数据要么落盘（MEMORY_AND_DISK）、要么直接放弃（MEMORY_ONLY）。
- Driver端（解决：是先适当预估结果集尺寸，然后再相应增加Driver侧的内存配置。）
  - 创建的数据集超过内存上限
  - 收集的结果集超过内存上限
- User Memory（用户自定义的数据结构，如数组、列表、字典）
  - 思路和Driver端的并无二致，也是先对数据结构的消耗进行预估，然后相应地扩大User Memory的内存配置
- Execution Memory
  - 误区：数据量比执行内存小就不会发生OOM，相反就会有一定的几率触发OOM问题
  - 实际上，数据量并不是决定OOM与否的关键因素，数据分布与Execution Memory的运行时规划是否匹配才是
  - 数据倾斜
    - 消除数据倾斜，让所有的数据分片尺寸都不大于 总内存/任务数量
    - 调整Executor线程池、内存、并行度等相关配置
  - 数据膨胀
    - 把数据打散，提高数据分片数量、降低数据粒度
    - 加大内存配置，结合Executor线程池调整

### spark的存储系统
- Rdd缓存
  - 将RDD以缓存的形式物化到内存或磁盘的过程
  - 好处
    - 一是通过截断DAG，可以降低失败重试的计算开销；
    - 二是通过对缓存内容的访问，可以有效减少从头计算的次数，从整体上提升作业端到端的执行性能。
- Shuffle 中间文件
  - 实际上就是Shuffle Map阶段的输出结果，这些结果会以文件的形式暂存于本地磁盘
- 广播变量：用于在集群范围内分发访问频率较高的小数据
  - 利用存储系统，广播变量可以在Executors进程范畴内保存全量数据
  

### Hive和spark的倾斜区别？（本质是mr和spark的区别）为什么现在不用mr了，都用spark？
- Hive是基于MapReduce的，而Spark是基于内存的。
- 在Hive中，数据倾斜是一个常见的问题，因为MapReduce是一种基于磁盘的计算模型，它需要将数据写入磁盘并进行排序和分组操作。
- 这些操作会导致一些任务比其他任务更慢，从而导致数据倾斜。
- Spark使用内存进行计算，因此可以避免这种问题。
- 此外，Spark还提供了许多优化技术，如动态分区和广播变量，以帮助处理数据倾斜问题。
- 关于为什么现在不用MapReduce了，主要是因为Spark具有更高的性能和更好的扩展性，而且它还提供了更多的功能和API

### mr的工作原理，Combiner是干嘛的？计算模型和底层架构
- MapReduce（MR）是一种分布式计算模型，用于处理大规模数据集。
- 它的工作原理是将大型数据集分成小块，然后在多个计算机上并行处理这些块。
- MapReduce计算模型由两个主要阶段组成：
  - Map阶段和Reduce阶段。在Map阶段，数据被分割成小块，并在多个计算机上并行处理。
  - 在Reduce阶段，Map阶段的输出被收集并合并，以生成最终结果。
- Combiner是MapReduce的一个可选组件，它可以在Map阶段之后执行本地合并操作，以减少Reduce阶段的数据传输量。
- Combiner通常用于执行本地聚合操作，例如计算平均值或求和。
- MapReduce的底层架构包括一个主节点和多个工作节点。
  - 主节点负责协调整个计算过程，而工作节点负责实际的计算任务

### Hadoop的整个生态系统和系统架构

### spark on hive和hive on spark的区别
- Spark on Hive
  - Spark on Hive 是Hive只作为存储角色，Spark负责sql解析优化，执行。
  - 这里可以理解为Spark 通过Spark SQL 使用Hive 语句操作Hive表 ,底层运行的还是 Spark RDD。
  - 具体步骤如下：
    - 通过SparkSQL，加载Hive的配置文件，获取到Hive的元数据信息；
    - 获取到Hive的元数据信息之后可以拿到Hive表的数据；
    - 通过SparkSQL来操作Hive表中的数据。
- Hive on Spark
  - Hive on Spark是Hive既作为存储又负责sql的解析优化，Spark负责执行。
  - 这里Hive的执行引擎变成了Spark，不再是MR，这个要实现比Spark on Hive麻烦很多, 必须重新编译你的spark和导入jar包，不过目前大部分使用的确实是spark on hive。
  - Hive默认使用MapReduce作为执行引擎，即Hive on MapReduce。
  - 实际上，Hive还可以使用Tez和Spark作为其执行引擎，分别为Hive on Tez和Hive on Spark。
  - 由于MapReduce中间计算均需要写入磁盘，而Spark是放在内存中，所以总体来讲Spark比MapReduce快很多。
  - 因此，Hive on Spark也会比Hive on MapReduce快。由于Hive on MapReduce的缺陷，所以企业里基本上很少使用了。
 

### spark的宽窄依赖是怎么划分的？
- 宽依赖，父RDD的一个分区会被子RDD的多个分区使用。
- 窄依赖，父RDD的分区最多只会被子RDD的一个分区使用。

### spark的RDD的了解
- 2大特性：分布式（partitions、partitioner）、容错性（dependencies和compute）
- 4个属性：partitions、partitioner、dependencies和compute属性，
  - 前两个属性锚定数据分片实体，并规定了数据分片在分布式集群中如何分布
  - 后两个属性用于在纵深方向构建DAG，通过提供重构RDD的容错能力保障内存计算的稳定性
  - partitions：RDD分布式数据实体中所有的数据分片，
  - partitioner：定义了划分数据分片的分区规则，如按哈希取模（HashPartitioner）或是按区间划分
  - dependencies：记录了生成RDD 所需的父依赖（或父RDD）
  - compute方法：封装了从父RDD到当前RDD转换的计算逻辑

#### HDFS的存储机制？
- 数据块（block），默认64M（1.x版本），128M（2.x版本），可通过dfs.block.size设置
- NameNode，管理命名空间、文件信息和块信息的映射
  - 有几种存储方式：内存元数据、命名空间镜像文件（fsImage）和编辑日志文件
    - 内存元数据，目的是提升性能，定期从磁盘加载一份镜像到内存中。
    - 命名空间镜像文件（fsImage），保存整个文件系统的目录树。
    - 编辑日志文件（edits），记录文件系统元数据发生的所有更改，如文件的删除或添加等操作信息。
    - HA：Secondary NameNode会适时合并fsimage和edits文件，以更新fsimage和减少edits大小的目的
      - Secondary NameNode也会在active的NameNode宕机时接管
  - Block Management：维护从Block到DataNode的映射关系；接受DN的注册和心跳等
- DataNode，存储文件的数据块（按顺序），生成与每个文件对应的数据块列表
  - Physical Storage：
- 副本数，每个文件被配置的副本数量
- Federation：增加多个NameNode，每个DataNode对每个NameNode通信（n对n）
  - Block Pool: 存储在dn上，管理某个NameNode上的所有块集合，归属于一个命名空间
  - 不同的NameNode控制不同的命名空间，互相不互通，单点故障不会升级为全集群故障
- 写数据 [HDFS写文件过程分析](http://shiyanjun.cn/archives/942.html)
  - 每个节点写到缓存区write就返回，然后继续写下一个块，然后由dn1一边写磁盘，一边把数据发给dn2，dn2同时写块1
  - nn等待3副本都写好（3个ack返回）后，写入才算成功（但写下一个块不必要接收完整的3个ack）
  - 如果有副本写失败，nn会异步复制，再复制写失败的副本
- 读数据 [HDFS读写过程分析](https://www.jianshu.com/p/7d1bdd23c460)
  - 客户端跟nn通信，获取初始几个块的地址（批量），这些块按距离排序过
    - nn中块信息是放在内存中，所以很快返回
    - 之后客户端直接和dn交互，扩展性比较强
  - 如果最接近的dn上的block损坏，再去下一个dn上获取

### hive数据存哪？内表外表区别和用法，分区分桶区别？
- 数据存放
  - 元数据：放在Mysql中
  - 数据：一般放在HDFS上
- 内表和外表
  - 内表：hive管理生命周期，drop时数据和元数据都删除
  - 外表：drop时只删元数据；建表时EXTERNAL修饰；
    - 一般用于数据需要与其他系统共享（所以不能删除）；在hive之外被修改
- 分区和分桶
  - 共同点：提高查询性能，减少扫描数据量；易于管理； 
  - 分区：
    - 根据一列或多列划分数据；形式上理解为文件夹；
    - 建表语句中没有的字段；
    - 数量可以增加
    - 可以使用不同的存储格式
  - 分桶（clustered by）：
    - 比分区更细粒度，划分为相同数量的桶（是文件，非文件夹）；如按name分桶，则把name属性hash值对3取模
    - 建表语句中有的字段；
    - 数量一旦指定，不可增加
    - 只能使用Hive的默认存储格式
    - 作用：一般用于抽样（从总共的m个桶中取n个）；也可以用于map-side join（保存相同列值的桶进行JOIN，减少数据量）


### HQL底层运行逻辑

### Sort by Order by的区别
- order by
  - 对输入做全局排序，因此只有一个Reducer，
  - 然而只有一个Reducer，会导致当输入规模较大时，消耗较长的计算时间
- sort by
  - 不是全局排序，其在数据进入reducer前完成排序，
  - 如果用sort by进行排序，并且设置mapred.reduce.tasks>1，则sort by只会保证每个reducer的输出有序，并不保证全局有序。
  - 使用sort by你可以指定执行的reduce个数(通过set mapred.reduce.tasks=n来指定)，对输出的数据再执行归并排序，即可得到全部结果。
- distribute by
  - 控制在map端如何拆分数据给reduce端的
  - hive会根据distribute by后面列，对应reduce的个数进行分发，默认是采用hash算法。
  - 在有些情况下，你需要控制某个特定行应该到哪个reducer，这通常是为了进行后续的聚集操作，distribute by刚好可以做这件事。因此，distribute by经常和sort by配合使用。
- cluster by
  - 除了具有distribute by的功能外还兼具sort by的功能
  - 排序只能是倒叙排序，不能指定排序规则为ASC或者DESC


### 为什么词频统计这么简单的问题需要MR？
- 大数据量单计算节点处理不下，每个计算节点独立运行map或reduce任务

### 星型模型和雪花模型区别
- 星型模型是一种简单的模型，它将事实表和维度表分别建模为一个星型结构。
  - 在星型模型中，每个维度表都只包含一个层次结构，并且所有的维度表都与事实表直接相连。
  - 这种模型的优点是简单易懂，易于查询和分析，但缺点是不够灵活，无法处理复杂的层次结构。
- 雪花模型是星型模型的扩展，它将维度表进一步规范化，以处理更复杂的层次结构。
  - 在雪花模型中，维度表被分解成多个表，每个表都包含一个层次结构。
  - 这些表通过外键关联起来，形成一个类似于雪花的结构。
  - 这种模型的优点是可以处理复杂的层次结构，缺点是查询和分析比较复杂，性能也可能受到影响。
- 举例
  - 星型：销售事实表，维度表包括产品维度表、客户维度表和时间维度表。这些维度表都与销售事实表直接相连，形成一个星型结构。
  - 事实表是销售事实表，维度表包括产品维度表、客户维度表和时间维度表。这些维度表都被分解成多个表，每个表都包含一个层次结构，形成一个类似于雪花的结构。
## INFO
- 默认情况下，spark为hdfs的每一个block（64M或128M）创建一个分区（且不允许比这个数值更小）

