### string 的 intern
如果字符串常量池已经包含一个等于此String对象的字符串，则返回字符串常量池中这个字符串的引用, 否则将当前String对象的引用地址（堆中，即当前字符串）添加到字符串常量池中并返回。

### hashmap
1. put
    - 1.7版本在多线程下put后的扩容过程（transfer方法）会死循环，新链表的顺序跟旧的链表是完全相反的，因此可能会发生死循环
    - 1.8版本链表顺序一样，不会产生死循环

### concurrenthashmap
1. Node数组+链表+红黑树的数据结构
2. 扩容时机
    - 数组tab长度小于64，且某个链表长于8，则扩容
    - 数组tab长度大于等于64，且某个链表长于8，则转变为红黑树
3. 1.7和1.8有的实现方式啥不同？ （https://blog.csdn.net/qq_32224047/article/details/108990363）
- 1.7 
  - 分段（segment）继承 ReentrantLock，通过段来加锁
  - concurrencyLevel，并发数（段数，默认16），初始化后不可扩容
  - segment内部是个数组+链表（参考HashMap），每个segment中put时加锁
  - put时先tryLock，重试1次（单核）或64次（多核）后，阻塞等待获取锁，获取到之后就执行put
  - get不加锁
- 1.8 
  - 使用采用 Node + CAS + synchronized 保证线程安全，每个数组的位置一个锁（若元素非空）
  - 引入红黑树，超过8时链表转红黑树
  - put，如果该 bin 尚未创建，只需要使用 cas 创建 bin；如果已经有了，锁住链表头进行后续 put 操作，元素添加至 bin 的尾部


### hashmap红黑树阈值为8原因：
- 随机hashCode下，转化为红黑树的概率服从泊松分布，阈值为8时概率为0.00000006
- 8的时候链表要比较8次，红黑树理想情况下只需要4次
- 为什么不一开始用红黑树？
  - 红黑树的左右旋比较耗时，数据小时性价比不如链表
- 为什么转回链表阈值是6？
  - 避免频繁的来回转换

### hashmap的扩容因子为什么是0.75
- 只需要回答为什么不是0.5和1就行，0.8其实也可以
- 0.5 空间利用率太低
- 1 可能会出现大量的Hash的冲突，底层的链表/红黑树变得异常复杂。
  - 对于查询效率不利

### 本地方法栈会发生垃圾回收吗？


#### 哪些场景会触发类的加载
- 使用new关键字实例化对象、读取或设置一个类的静态字段（final字段除外），调用一个类的静态方法
- 使用java.lang.reflect包对类进行反射调用
- 初始化一个类时，发现其父类还未初始化，此时要初始化父类 
  - 接口初始化时，不要求其所有父接口全部完成初始化，只有在用到时才会初始化
- 虚拟机启动时，用户需要指定一个要执行的主类，虚拟机要先初始化这个类
- 1.7以上，一个MethodHandle实例最后解析结果为REF_getStatic、REF_putStatic、REF_invokeStatic且这个句柄对应的类还没进行过初始化

#### JVM怎么创建一个对象
- 检查类是否被加载、解析和初始化过
- 虚拟机为新生对象分配内存 
  - 线程安全解决
    - 如果在方法体内部生成，且未在外部引用（逃逸），尝试在栈上分配（把实例变量取出来初始化，不初始化对象）
    - 按线程划分空间，即本地线程分配缓冲（Thread Local Allocation Buffer）
      - JVM在Eden区为每条线程划分的一块私有缓冲内存，避免多个线程竞争
    - 采用CAS配上失败重试的方式保证更新操作的原子性（在空白内存处分配）
- 初始化内存空间（除对象头）为零值
- 保证实例变量不赋值就直接使用
- 对对象进行必要的设置（如哈希码、GC年龄，锁状态）
  - 放置于对象头中
- init方法（构造函数，应用程序角度的第一步）

#### HashMap和TreeMap的区别
- HashMap 是基于哈希表实现的，它使用哈希函数将键映射到桶中，然后在桶中查找值。
  - HashMap 的查询、插入和删除操作的时间复杂度都是 O(1)。但是，HashMap 中的元素是无序的，因此不能保证元素的顺序。
- TreeMap 是基于红黑树实现的，它可以保证元素按照键的自然顺序排序。（也支持自定义排序）
  - TreeMap 的查询、插入和删除操作的时间复杂度都是 O(log n)。但是，由于红黑树的平衡性，TreeMap 的性能比 HashMap 差一些。

#### CountDownLatch是什么？应用场景是什么

### 字符串用常量的原因
1. 字符串常量池的实现，多个变量指向池中的同一个，性能高
2. 安全性，不可变，不会被黑客改变
3. 线程安全，多个线程不需要同步
4. hashCode是固定的，适合作为map的键

### 创建线程的几种方式
1. 继承Thread类
2. 实现Runnable接口
3. 实现Callable接口
4. 线程池 

### 线上问题排查
1. jstack（1.5）生成thread dump，记录JVM在某一时刻各个线程执行的情况，是一个文本文件
    - 需要在多个时间段提出多个 Thread Dump信息，然后综合进行对比分析，单独分析一个文件是没有意义的。
2. jstat命令用于见识虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、jit编译等运行数据
3. "VM Thread" 是 JVM 自身启动的一个线程, 它主要用来协调其它线程达到安全点(Safepoint)
4. jmap生成Heap Dump文件
    - jhat 是JDK自带的用于分析JVM Heap Dump文件的工具，使用下面的命令可以将堆文件的分析结果以HTML网页的形式进行展示：jhat <heap-dump-file>
    
### 频繁GC
1. 可能是内存泄漏，内存使用完了没释放；jmap（jmap -histo  pid）看对象的存活，哪些对象数太多
2. 内存设置问题，jstat看看，根据业务，新生代、老年代和永久代的设置情况


### OOM如何排查？
1. 原因
    - 分配的少了：比如虚拟机本身可使用的内存（一般通过启动时的VM参数指定）太少。
    - 应用用的太多，并且用完没释放，浪费了。此时就会造成内存泄露或者内存溢出。
2. 举例
    - java.lang.OutOfMemoryError: Java heap space ------>java堆内存溢出，此种情况最常见，
      - 一般由于内存泄露或者堆的大小设置不当引起。
      - 对于内存泄露，需要通过内存监控软件查找程序中的泄露代码，
      - 而堆大小可以通过虚拟机参数-Xms（最小），-Xmx等修改。
    - java.lang.OutOfMemoryError: PermGen space 或 java.lang.OutOfMemoryError：
      - MetaSpace ------>java方法区，（java8 元空间）溢出了，一般出现于大量Class或者jsp页面，
      - 或者采用cglib等反射机制的情况，因为上述情况会产生大量的Class信息存储于方法区。
      - 此种情况可以通过更改方法区的大小来解决，使用类似-XX:PermSize=64m -XX:MaxPermSize=256m的形式修改。
      - 另外，过多的常量尤其是字符串也会导致方法区溢出。
    - java.lang.StackOverflowError ------> 
      - 不会抛OOM error，但也是比较常见的Java内存溢出。
      - JAVA虚拟机栈溢出，一般是由于程序中存在死循环或者深度递归调用造成的，栈大小设置太小也会出现此种溢出。
      - 可以通过虚拟机参数-Xss来设置栈的大小。

### 自己实现一个阻塞队列
- 数据结构
  - 一个reentrantLock，派生两个condition，一个notEmpty，一个notFull，一个object数组存放元素，
  - 一个size标识队列规模（实际的元素个数，非最大值），一个head和tail，标识头尾元素的下标
- 流程
  1. 初始化：object数组初始化为size大小
  2. put方法：先lock.lock()，当size == object数组的len时，代表队列满，notFull.await()，直到被唤醒；
     - 放置元素在tail位置，tail+1，size++；然后notEmpty.signal()，唤醒可能在等待的线程；最后在finally块中释放锁
  3. take方法：先lock.lock()，当size == 0时，代表队列空，notEmpty.await()，直到被唤醒；
     - 取出head处元素，强转为E；head++，size++；然后notFull.signal()，唤醒可能在等待的线程；最后在finally块中释放锁
  4. 注意：当head或tail为数组长度时，要及时更新为0

### 多线程交替打印
1. LockSupport.park()和LockSupport.unpark(thread)方法，每次线程打印后调用park方法挂起自己，同时unpark另一个线程
   - 要注意LockSupport.park()和LockSupport.unpark(thread)在线程内部调用
   - 线程t1（for循环打印1 3 5...）：System.out.println(i);  LockSupport.unpark(t2);  LockSupport.park();
   - 线程t2（for循环打印2 4 6...）：LockSupport.park();  System.out.println(i);  LockSupport.unpark(t1);
2. 阻塞队列（长度为1的两个ArrayBlockingQueue，或两个SynchronousQueue），线程从一个队列中取，同时往另一个队列塞，队列为空则自动阻塞
   - 线程t1（for循环打印1 3 5...）：q1.put(i);  System.out.println(q2.take());
   - 线程t2（for循环打印2 4 6...）：System.out.println(q1.take()); q2.put(i);
3. 不使用锁，用一个AtomicInteger，一个线程在其为偶数时打印，一个奇数打印，打印完++
   - 线程t1（while循环，小于100）：判断当前counter.get()是否是奇数，是则打印，然后counter.getAndIncrement()
   - 线程t2（while循环，小于100）：同上，判断是否为偶数
4. ReentrantLock和Condition，每个线程先打印，然后调用condition的signal，唤醒另一个线程；再调用wait，自己挂起，等待被唤醒
   - 1个Lock，一个Condition，一个count（注意在finally中释放锁）
   - 线程t1（while循环，count <=100）
     - 如果count是奇数，打印并+1，并condition.signal();
     - 如果count是偶数，直接condition.await(); 阻塞等待
   - 线程t2（while循环，count <=100）
     - 如果count是偶数，打印并+1，并condition.signal();
     - 如果count是技术，直接condition.await(); 阻塞等待
5. synchronized方法，同一把锁，每个线程打印后，notify另一个，自己wait；另一个线程被唤醒后，打印，再notify，然后自己wait
    - 类似4，变成notify和wait


### LinkedBlockingQueue和ConcurrentLikedQueue（无边界）的区别，为什么要有两个？
有block则代表提供了阻塞api；有concurrent代表方法加了锁，线程安全；有queue则是单向队列，有deque则是双端队列；有linked是基于链表实现，有array是基于数组实现的

### ConcurrentLinkedQueue
1. 使用约定
    - 不允许null入列
    - 在入队的最后一个元素的next为null
    - 队列中所有未删除的节点的item都不能为null且都能从head节点遍历到
    - 删除节点是将item设置为null, 队列迭代时跳过item为null节点
    - head节点跟tail（多线程不一定是最后一个，可能是倒数第二个）不一定指向头节点或尾节点，可能存在滞后性
2. 入列
    - 死循环，就是不停使用cas判断直到添加元素入队成功
    - 死循环中运行casNext和casTail方法，确保队列在 入列时/tail队尾在移动改变时 是原子操作
    - 线程1线程2同时入列：利用cas解决碰撞，线程安全
    - 线程1遍历，线程2入列：线程1遍历，线程2很有可能进行入列出列操作， 所以ConcurrentLinkedQueue 的size（size()方法是O（n）的，且线程不安全）是变化。换句话说，要想安全遍历ConcurrentLinkedQueue 队列，必须额外加锁。
3. 出列
    - 死循环，不断cas将操作节点的item设置null， 表示出列成功
    - 一旦出列成功需要对head进行移动


### jvm的命名空间
- 每个类加载器都有自己的命名空间。和我们Java中的Package的概念是一样的，和XML中的namespace的概念类似。同一个命名空间内的类是相互可见的，命名空间由该加载器及所有父加载器所加载的类组成。
- 子加载器加载的类能看见父加载器的类，但是父加载器看不到子加载器加载的类
- 在同一个命名空间中，不会出现类的完整名字（包括类的包名）相同的两个类；在不同的命名空间中，有可能会出现类的完整名字（包括类的包名）相同的两个类。



### 系统线程状态 (Native Thread Status)
####deadlock
死锁线程，一般指多个线程调用期间进入了相互资源占用，导致一直等待无法释放的情况。

####runnable
一般指该线程正在执行状态中，该线程占用了资源，正在处理某个操作，如通过SQL语句查询数据库、对某个文件进行写入等。

#### blocked
线程正处于阻塞状态，指当前线程执行过程中，所需要的资源长时间等待却一直未能获取到，被容器的线程管理器标识为阻塞状态，可以理解为等待资源超时的线程。

#### waitting on condition（线程状态为WAITING或TIMED_WAITING）
线程正处于等待资源或等待某个条件的发生，具体的原因需要结合下面堆栈信息进行分析。
1. 如果堆栈信息明确是应用代码，则证明该线程正在等待资源，一般是大量读取某种资源且该资源采用了资源锁的情况下，线程进入等待状态，等待资源的读取，或者正在等待其他线程的执行等。
2. 如果发现有大量的线程都正处于这种状态，并且堆栈信息中得知正等待网络读写，这是因为网络阻塞导致线程无法执行，很有可能是一个网络瓶颈的征兆：
    - 网络非常繁忙，几乎消耗了所有的带宽，仍然有大量数据等待网络读写；
    - 网络可能是空闲的，但由于路由或防火墙等原因，导致包无法正常到达；
    - 所以一定要结合系统的一些性能观察工具进行综合分析，比如netstat统计单位时间的发送包的数量，看是否很明显超过了所在网络带宽的限制；观察CPU的利用率，看系统态的CPU时间是否明显大于用户态的CPU时间。这些都指向由于网络带宽所限导致的网络瓶颈。
3. 还有一种常见的情况是该线程在 sleep，等待 sleep 的时间到了，将被唤醒。

#### waiting for monitor entry 或 in Object.wait()  (线程状态为BLOCKED)
每个Monitor在某个时刻只能被一个线程拥有，该线程就是 "Active Thread"，而其他线程都是 "Waiting Thread"，分别在两个队列 "Entry Set"和"Waint Set"里面等待。其中在 "Entry Set" 中等待的线程状态是 waiting for monitor entry，在 "Wait Set" 中等待的线程状态是 in Object.wait()。
1.  "Entry Set"里面的线程。
    - 我们称被 synchronized 保护起来的代码段为临界区，
    - 当一个线程申请进入临界区时，它就进入了 "Entry Set" 队列中，这时候有两种可能性：
        - 该Monitor不被其他线程拥有，"Entry Set"里面也没有其他等待的线程。本线程即成为相应类或者对象的Monitor的Owner，执行临界区里面的代码；此时在Thread Dump中显示线程处于 "Runnable" 状态。
        - 该Monitor被其他线程拥有，本线程在 "Entry Set" 队列中等待。此时在Thread Dump中显示线程处于 "waiting for monitor entry" 状态。
    - 临界区的设置是为了保证其内部的代码执行的原子性和完整性，但因为临界区在任何时间只允许线程串行通过，这和我们使用多线程的初衷是相反的。如果在多线程程序中大量使用synchronized，或者不适当的使用它，会造成大量线程在临界区的入口等待，造成系统的性能大幅下降。如果在Thread Dump中发现这个情况，应该审视源码并对其进行改进。

2. "Wait Set"里面的线程
    - 当线程获得了Monitor，进入了临界区之后，如果发现线程继续运行的条件没有满足，它则调用对象（通常是被synchronized的对象）的wait()方法，放弃Monitor，进入 "Wait Set"队列。只有当别的线程在该对象上调用了 notify()或者notifyAll()方法，"Wait Set"队列中的线程才得到机会去竞争，但是只有一个线程获得对象的Monitor，恢复到运行态。"Wait Set"中的线程在Thread Dump中显示的状态为 in Object.wait()。通常来说，当CPU很忙的时候关注 Runnable 状态的线程，反之则关注 waiting for monitor entry 状态的线程。



### Java对异常的设计以及为何这么设计
1. error：Error是程序无法处理的错误，它是由JVM产生和抛出的，
   - 比如OutOfMemoryError、ThreadDeath等。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止。
2. exception：Exception是程序本身可以处理的异常，这种异常分两大类运行时异常和非运行时异常。程序中应当尽可能去处理这些异常。
    - RuntimeException：运行时异常都是RuntimeException类及其子类异常，
      - 如NullPointerException、IndexOutOfBoundsException等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。
      - 这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
    - 受检异常：非运行时异常是RuntimeException以外的异常，类型上都属于Exception类及其子类。
      - 从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。
      - 如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。


### volatile用处，如何实现的
- synchronized 关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而 volatile 关键字在某些情况下性能要优于 synchronized ，
- 但是要注意 volatile 关键字是无法替代 synchronized 关键字的，因为 volatile 关键字无法保证操作的原子性。通常来说，使用 volatile 必须具备以下 2 个条件：
    - 对变量的写操作不依赖于当前值
    - 该变量没有包含在具有其他变量的不变式中，例如
        ```java
          public class NumberRange {
              private volatile int lower, upper;
          
              public int getLower() { return lower; }
              public int getUpper() { return upper; }
          
              public void setLower(int value) { 
                  if (value > upper) 
                      throw new IllegalArgumentException();
                  lower = value;
              }
          
              public void setUpper(int value) { 
                  if (value < lower) 
                      throw new IllegalArgumentException();
                  upper = value;
              }
          }
        ```
        - 初始状态是(0, 5)，某个时刻线程A调用 setLower(4)的同时线程B调用setUpper(3)，这个时候两个调用都可以通过边界检查，最后得到(4, 3)，这个边界结果显然是没有意义的，但是volatile在这里并不能起作用，这种情况应该使用锁来保证边界结果的有效性。
- volatile和synchronize的区别
    - volatile比synchronized更轻量级。
    - volatile没有synchronized使用的广泛。
    - volatile不需要加锁，比synchronized更轻量级，不会阻塞线程。
    - 从内存可见性角度看，volatile读相当于加锁，volatile写相当于解锁。
      - volatile可以保证对变量的访问均需要从共享内存中获取对它的改变必须同步刷新回共享内存
      - synchronized确保每一个时刻只有一个线程处于方法或同步块中，保证线程对变量访问的可见性和排他性
    - synchronized既能保证可见性，又能保证原子性，而volatile只能保证可见性，无法保证原子性。
    - volatile本身不保证获取和设置操作的原子性，仅仅保持修改的可见性。但是java的内存模型保证声明为volatile的long和double变量的get和set操作是原子的。
- 使用场景
    - 作为状态标识，一般来说状态值的改变都是完全独立于上一个状态的
    - 多线程初始化对象，一个线程赋值，多个线程使用
    - 对象的属性，由一个线程修改，其他线程立即可见
    - 高性能读-写锁策略，volatile保证读可见性，锁保证赋值操作的原子性

#### synchronized能禁止指令重排序吗
- synchronized 的原理和锁相同，实际上是在monitorenter和monitorexit处增加了两个院子指令，构成了内存栅栏
- 作用是保证monitorenter之后的指令不会逃逸到前面；monitorexit之前的指令也不会逃逸到后面
- 同时保证了这两个字节码前的副作用都生效
- 因此答案只会保证栅栏内外不会重排，栅栏内的不会禁止重排


### 线程池锁回收空闲线程
- 超过corePoolSize的空闲线程由线程池回收，线程池Worker启动跑第一个任务之后就一直循环遍历线程池任务队列，超过指定超时时间获取不到任务就remove Worker，最后由垃圾回收器回收。
- 另外，线程池在提交任务时新建核心线程（若需要）并执行task

### 线程池关闭
- shutdown：启动有序关闭，其中执行先前提交的任务和队列中等待的任务，但不会接受新任务。
    - 一定要确保任务里不会有永久阻塞等待的逻辑，否则线程池就关闭不了。
- shutdownNow：尝试终止所有正在执行的任务，并停止处理等待队列中的的任务，最后将所有未执行的任务列表的形式返回，此方法会将任务队列中的任务移除并以列表形式返回。
  - 此方法不会等待正在执行的任务执行完毕，要等待任务执行完毕可以使用awaitTermination()方法
  - 因为此方法底层实现是通过 Thread 类的interrupt()方法终止任务的，所以interrupt()未能终止的任务可能无法结束。
- 记得shutdownNow和shuwdown调用完，线程池并不是立刻就关闭了，要想等待线程池关闭，还需调用awaitTermination方法来阻塞等待。


### crm采用标记清理算法，如何解决内存碎片问题
对老年代的空间进行Compact（这个过程会stop the world，停顿时间边长）
- 其中参数UseCMSCompactAtFullCollection(默认true)和 CMSFullGCsBeforeCompaction(默认0)，所以默认每次的主动GC都会对老年代的内存空间进行压缩
- 执行了System.gc()
- 如果新生代的晋升担保会失败

### 线程池
1. 参数
    - corePoolSize:线程池中的常驻核心线程数，在创建了线程池后，当有请求任务进来之后，就会安排池中的线程去执行请求任务，近似理解为今日当值线程，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放入缓存队列中。
    - maximumPoolSize:线程池能够容纳同时执行的最大线程数，此值必须大于等于1
    - keepAliveTime:多余的空闲线程的存活时间。当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，多余空闲线程会被销毁直到只剩下corePoolSize个线程为止，默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池找那个的线程数不大于corePoolSize。
    - unit:keepAliveTime的单位
    - workQueue：任务队列，被提交但尚未被执行的任务
    - threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般默认即可
    - handler：拒绝策略，表示当队列满了并且工作线程大于等于线程池最大线程数（maximumPoolSize）时如何处理
2. 拒绝策略
    - CallerRunsPolicy（调用者运行策略）
    - AbortPolicy（中止策略）：抛异常
    - DiscardPolicy（丢弃策略）：不做任何事情
    - DiscardOldestPolicy（弃老策略）：如果线程池未关闭，就弹出队列头部的元素，然后尝试执行
3. 常用的线程池（5种）
    - FixedThreadPool：固定大小（最大=核心），keepAliveTime为0，无界队列
    - CachedThreadPool：核心0，最大线程数为int最大值，队列采用SynchronousQueue，不存储任务，有新任务就起线程运行之，keepAliveTime为60s
    - SingleThreadExecutor：单线程池，max和core都为1，keepAliveTime为0，队列无界
    - ScheduledThreadPool：处理延时任务或定时任务，队列为DelayQueue（无界队列），内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
    - newWorkStealingPool：基于ForkJoinPool，不是ThreadPoolExecutor，使用所有可用处理器作为目标并行度，创建一个窃取线程的池

### G1回收器
- 特点：高吞吐、没有内存碎片、收集时间可控，整体看是基于标记-整理算法，局部看是基于复制算法（2个Region之间）
1. 把堆划分为大小相等的Region（2048个），保留新生代和老年代，但不再物理隔离
2. 每次回收价值最大的Region（价值：回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的回收时间，优先回收价值最大的
3. 在回收一个Region的时候不需要执行全堆扫描，只需要检查它的RS（Remembered Set，记录外部指向本Region的所有引用，可能跨代）就可以找到外部引用，而这些引用就是initial mark的根之一
4. G1还有一个及其重要的特性：软实时（soft real-time）。所谓的实时垃圾回收，是指在要求的时间内完成垃圾回收。
   - “软实时”则是指，用户可以指定垃圾回收时间的限时，G1会努力在这个时限内完成垃圾回收，但是G1并不担保每次都能在这个时限内完成垃圾回收。
5. 回收过程
    - 初始标记(Initial Marking)：这个阶段是STW(Stop the World )的，所有应用线程会被暂停，标记出从GC Root开始直接可达的对象。（触发一次年轻代GC）
    - 并发标记：从GC Roots开始对堆中对象进行可达性分析，找出存活对象，耗时较长。
    - 最终标记(Final Marking)：标记那些在并发标记阶段发生变化的存活的对象，将被回收。（非全部扫描，比较快）
    - 独占清理(cleanup,STW): 计算各个区域的存活对象和GC回收比例,并进行排序，识别可以混合回收的区域。为下阶段做铺垫。是STW的。
    - 并发清理阶段: 识别并清理完全空闲的区域。
      - 因为要保证时间，所以Mix GC时清理所有的年轻代和价值高的老年代Region
6. GC模式
    - YoungGC年轻代收集：在分配一般对象（非巨型对象）时，当所有eden region使用达到最大阀值并且无法申请足够内存时，会触发一次YoungGC。
      - 每次younggc会回收所有Eden以及Survivor区，并且将存活对象复制到Old区以及另一部分的Survivor区。
    - mixed gc：当越来越多的对象晋升到老年代old region时，为了避免堆内存被耗尽，虚拟机会触发一个混合的垃圾收集器，即mixed gc，
      - 该算法并不是一个old gc，除了回收整个young region，还会回收一部分的old region，
      - 这里需要注意：是一部分老年代，而不是全部老年代，可以选择哪些old region进行收集，从而可以对垃圾回收的耗时时间进行控制。（用的是5的回收过程）
    - G1没有fullGC概念，需要fullGC时，调用serialOldGC进行全堆扫描（包括eden、survivor、o、perm）。
7. young GC过程
   年轻代垃圾回收只会回收Eden区和Survivor区。
- 扫描根 -> 更新RSet -> 处理RSet -> 复制对象（如果Survivor空间不够,Eden空间的部分数据会直接晋升到老年代空间） -> 处理引用
- GC root对象
  - 虚拟机栈（栈帧中的本地变量表）中引用的对象
  - 方法区中类静态属性引用的对象
  - 方法区中常量引用的对象
  - 本地方法栈中JNI（Native方法）引用的对象

### 垃圾回收器
- java8默认：Parallel Scavenge（多线程、复制算法） + Parallel Old（多线程、标记-整理算法）
- Parallel Scavenge ：关注点在于达到一个可控制的吞吐量（用户代码时间/CPU总时间）CMS等关注尽可能缩短垃圾收集时用户线程停顿的时间
- Parallel Old：Parallel Scavenge的老年代版本，多线程 + 标记-整理算法
- CMS（Concurrent Mark Sweep）：JDK 14开始弃用，JDK15中完全删除
  - CMS收集器在Minor GC时会暂停所有的应用线程，并以多线程的方式进行垃圾回收。
  - 在Full GC时不再暂停应用线程，而是使用若干个后台线程定期的对老年代空间进行扫描，及时回收其中不再使用的对象。
  - 缺点：
    - 无法处理碎片化的内存，其老年代采用 标记-清除回收策略，因此会有内存碎片问题，有时候不得不进行一次Full GC（新生代、老年代和永久代都GC）
    - 不能充分使用内存，并发标记和并发清理阶段，用户线程是还在继续运行的，程序在运行自然就还会伴随有新的垃圾对象不断产生，
      - 而这部分的垃圾对象是出现在标记过程结束以后，CMS无法在当次收集中处理掉它们，只好留在下次垃圾收集时再清理掉。
    - 进行垃圾回收时需要占用 CPU 资源，可能会影响应用程序的性能


### 垃圾回收算法和特点？
- 标记-清除算法
    - 该算法分为两个阶段，标记阶段和清除阶段。
    - 标记阶段遍历所有的对象，标记出所有需要回收的对象，清除阶段则回收被标记的对象。
    - 该算法的优点是不需要额外的空间，缺点是会产生内存碎片。
- 复制算法：
    - 该算法将内存分为两个区域，每次只使用其中一个区域。
    - 当这个区域用完了，就将还存活的对象复制到另一个区域中，然后清除当前区域中的所有对象。
    - 该算法的优点是简单高效，缺点是需要额外的空间。
- 标记-整理算法：
    - 该算法是标记-清除算法的改进版，它在标记阶段和清除阶段之间增加了一个整理阶段。
    - 在整理阶段，它会将所有存活的对象向一端移动，然后清除掉边界以外的所有内存。
    - 该算法的优点是不会产生内存碎片，缺点是需要额外的空间和时间.


### 引用类型
- 强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。
- 如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。
- 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
- “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。（虚引用主要用来跟踪对象被垃圾回收器回收的活动。）


### ThreadLocal内存泄漏
- ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，
  - 这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value
  - 如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreadLocalMap -> Entry -> value永远无法回收，造成内存泄漏。
- 其实，ThreadLocalMap的设计中已经考虑到这种情况，也加上了一些防护措施：在ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value。
- 但是这些被动的预防措施并不能保证不会内存泄漏：
    - 使用线程池的时候，这个线程执行任务结束，ThreadLocal对象被回收了，线程放回线程池中不销毁，这个线程一直不被使用，导致内存泄漏。
    - 分配使用了ThreadLocal又不再调用get(),set(),remove()方法，那么这个期间就会发生内存泄漏。
    - 每次使用完ThreadLocal，都调用它的remove()方法，清除数据。

### 什么是线程池预热？预热的好处是？
- 在系统启动时，提前创建一定数量的线程，以便在系统运行时，能够快速响应请求，减少线程创建的时间，从而提高系统的性能。
- 预热的好处是可以避免在系统运行时，因为线程创建时间过长而导致的性能瓶颈。
- 可以启动所有核心线程（prestartAllCoreThreads），也可以只启动一个核心线程（prestartCoreThread，如果所有核心线程都被启动，则返回false）

### 为什么youngGC很频繁？
- 要看youngGC的效果
  - 如果老年代一直增加，且后面的FullGC也不能回收内存，则可能是内存泄露了
  - 如果FullGc回收了很多内存，表示年轻代给的小，无法分配内存导致频繁gc，此时可以调大年轻代内存，让对象在年轻代中被回收

### thread有几种状态，和操作系统的对应关系
1. java线程状态
    - NEW
    - RUNNABLE：对应操作系统的READY和RUNNING，即此状态的线程可能已经在运行，也可能在等待CPU运行时间
    - BLOCKED
      - 表示线程正在等待获取锁，以便进入同步代码块或方法。当另一个线程持有锁时，线程将进入BLOCKED状态，直到它获得锁为止
    - WAITING
      - 无限期等待，线程正在等待其他线程执行某个操作，例如调用Object.wait()或Thread.join()方法。
    - TIMED_WAITING
      - 限期等待，不会被分配CPU执行时间，一定时间后会被系统自动唤醒
    - TERMINATED
2. 操作系统线程状态
    - NEW：进程正在被创建
    - READY：进程等待分配处理器
    - RUNNING：指令正在被执行
    - WAITING：等待进程某个事件发生（如IO完成），对应java的BLOCKED，WAITING和TIMED_WAITING
    - TERMINATED：进程完成执行


### 同步，异步，阻塞，非阻塞
同步异步针对于发起调用者
1. 同步和异步关注的是消息通信机制 (synchronous communication/ asynchronous communication)
    - 所谓同步，就是在发出一个*调用*时，在没有得到结果之前，该*调用*就不返回。但是一旦调用返回，就得到返回值了。
    - 而异步则是相反，*调用*在发出之后，这个调用就直接返回了，所以没有返回结果。换句话说，当一个异步过程调用发出后，调用者不会立刻得到结果。而是在*调用*发出后，*被调用者*通过状态、通知来通知调用者，或通过回调函数处理这个调用。
2. 阻塞和非阻塞关注的是程序在等待调用结果（消息，返回值）时的状态.
    - 阻塞调用是指调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会返回。
    - 非阻塞调用指在不能立刻得到结果之前，该调用不会阻塞当前线程。



### CompletableFuture
#### Future的局限
- 不能手动完成
- Future 的结果在非阻塞的情况下，不能执行更进一步的操作，必须调用get阻塞等待
- 多个 Future 不能串联在一起组成链式调用
- 不能组合多个 Future 的结果
- 没有异常处理
- 
#### 解决
- 使用CompletableFuture.complete(T value) 手工的完成一个 Future
  - 其作用为：手动完成一个Future，并把结果置为value；以便所有的客户端都能获取到指定的结果
- 使用 runAsync() 运行异步计算或 使用 supplyAsync() 运行一个异步任务并且返回结果
- 可以使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture
- 使用 thenCompose() /thenCombine() 组合两个独立的future，前者用于当一个future依赖另外一个future的时候用来组合两个future，后者用于当两个独立的Future都完成的时候，用来做一些事情（还有allOf()/ anyOf() 可以使用）
- 使用 exceptionally() 回调处理异常 / 使用 handle() 方法处理异常

### future，futureTask和CompletableFuture的区别
- 应用场景
    - Java中线程的实现方式一般有实现Runable接口，实现Callable接口，继承Thread类三种。
    - 而Runable和Thread的run方法都是void，也就是没有返回值，
        - 所以这种情况下获取线程的返回值就要使用线程间通信的手段，一般比较麻烦；
    - 或者使用Callable接口，但是Callable接口本身只能支持同步的结果获取
- future是个接口，不同的实现是不一样的，主要用于异步获取结果
- 常见的是FutureTask（java 5）（线程池提交Callback）是直接依赖LockSupport.park(nanos)/unpark的，Lock锁/AQS也是依赖这个。
    - get时会检查有没有完成，没完成会进入阻塞，等到任务的流程跑完后，会塞入结果，然后唤醒等待的线程。
- 另一个常见的是CompletableFuture（Java 8），get的时候的特点是先自旋一定次数，尝试获取结果，拿不到再进入阻塞
- CompletableFuture
    - CompletableFuture 支持链式调用和组合操作，
    - 只要任务完成，即执行我们设置的函数（不用再去考虑什么时候任务完成）
        - thenApply()、thenAccept()、thenRun()
    - 如果发生异常，同样会执行我们处理异常的函数，甚至连默认返回值都有（异常情况处理更加省力）
        - handle()、exceptionally()
    - 如果有复杂任务，比如依赖问题，组合问题等，同样可以写好处理函数来处理（能应付复杂任务的处理）
        - thenCombine()、thenCompose()
- FutureTask
    - 只能通过get方法或者死循环判断isDone来获取。
    - 实现了Runable接口，所以我们可以直接将FutureTask提交到线程池执行，同时也可以获取执行结果
        - CompletableFuture.supplyAsync()也支持线程池



### synchronized和ReentrantLock的加锁和解锁能在不同线程吗？
- 不能，二者加锁时都会记录线程号，syn记录在对象头，ren记录在AQS队列


### Java有几种锁，区别是什么？
- synchronized锁：是Java中最基本的锁，它是一种重量级锁，适用于线程竞争不激烈的场景。
    - 它是一种非公平锁，即线程获取锁的顺序是不确定的。
    - 它是可重入锁，即同一个线程可以多次获取同一个锁而不会死锁。
    - 它是独享锁，即同一时间只能有一个线程获取到锁
- ReentrantLock锁：是Java中的另一种重量级锁，它是一种可重入锁，适用于线程竞争激烈的场景。
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是独享锁，即同一时间只能有一个线程获取到锁。
- ReentrantReadWriteLock锁：是Java中的读写锁，它是一种轻量级锁，适用于读多写少的场景。
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是共享锁，即多个线程可以同时获取到读锁，但只有一个线程可以获取到写锁。
- StampedLock锁：是Java中的另一种读写锁，它是一种乐观锁，适用于读多写少的场景。
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是共享锁，即多个线程可以同时获取到读锁，但只有一个线程可以获取到写锁。
- Semaphore锁：是Java中的信号量，它是一种轻量级锁，本质上就是一个信号计数器，用于限制同一时间的最大访问数量
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是共享锁，即多个线程可以同时获取到锁。
- CountDownLatch锁：是Java中的倒计时锁，它是一种轻量级锁，适用于等待其他线程完成后再执行的场景。
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是共享锁，即多个线程可以同时获取到锁。
    - 公平锁的实现：在AQS的队列中，查看队列中等待最长的线程是不是当前线程，如果是则获取（实现了先到先得）
- CyclicBarrier锁：是Java中的循环屏障锁，它是一种轻量级锁，适用于等待其他线程到达某个状态后再执行的场景。
    - 它是一种非公平锁，但可以通过构造函数指定为公平锁。
    - 它是共享锁，即多个线程可以同时获取到锁
    - 与CountDownLatch锁的区别
      - 可以重复使用，后者不行（CyclicBarrier所有线程到达栅栏时，会重置到初始状态）
      - CyclicBarrier还可以指定一个可选的Runnable对象，所有线程到达栅栏位置时，该对象被执行

### AQS（AbstractQueuedSynchronizer）队列的原理
- AQS本身没有公平\非公平的概念，需要实现类自己实现
- 维护了一个 state（共享资源变量）和一个 FIFO 线程等待队列（CLH 队列），多个线程竞争 state 被阻塞时就会进入此队列中。
  - ReentrantLock、ReentrantReadWriteLock、CountDownLatch、Semaphore这些常用的实现类都是基于AQS实现的
  - CyclicBarrier 的底层实现是基于 ReentrantLock 和 Condition 的组合使用，而不是基于 AbstractQueuedSynchronizer
- state 是用 volatile 修饰的一个 int 类型的共享资源变量
  - 作用是可重入，当线程多次加锁时，state=线程加锁次数
  - 每次解锁state-1，当为0时代表解锁成功
- 队列：是一个双端队列，包含目前正在等待锁的线程，每个线程被包装成一个Node
  - node有四种状态
    - CANCELLED：线程处于超时或者中断状态，值为1
    - SIGNAL表示后继节点被唤醒，值为-1
    - CONDITION表示线程处于等待队列中，无法在同步队列中使用，值为-2，直到调用signal方法后将其转移到同步队列中
    - PROPAGATE表示下一个共享模式下获取同步状态会被持续传播下去，值为-3
- 支持独占和共享
  - isHeldExclusively()；// 是否为独占模式；但是只有使用到了Condition的，才需要去实现它。例如：ReentrantLock。 
  - boolean tryAcquire(int arg); // 独占模式；尝试获取资源，成功返回true，失败返回false。 
  - boolean tryRelease(int arg) ; // 独占模式；尝试释放资源，成功返回true，失败返回false。 
  - int tryAcquireShared(int arg); // 共享模式；尝试获取资源，负数表示失败；0表示成功，但是没有剩余可用资源了；正数表示成功，且有剩余可用资源。 
  - boolean tryReleaseShared(int arg) ; // 共享模式；尝试释放资源，若释放资源后允许唤醒后续等待节点返回true，否则返回false。
- 获取锁的过程
  - 当线程CAS获取锁失败，将当前线程入队列，把前驱节点状态设置为SIGNAL状态（表示自己要被唤醒），并将自己挂起。 
- 解锁的过程（简化：把state置0，唤醒下一个合法的节点，被唤醒的节点线程自然就会去获取锁）
  - 外界调用unlock方法时，实际上会调用AQS的release方法，而release方法会调用子类tryRelease方法（又回到了ReentrantLock中）
  - tryRelease会把state一直减（锁重入可使state>1），直至到0，当前线程说明已经把锁释放了
  - 随后从队尾往前找节点状态需要 < 0，并离头节点最近的节点进行唤醒
    - 从后往前找
      - 原因1：节点入队并不是原子操作，pred.next = node在CAS之后执行，此时从前往后找，就找不到了
      - 原因2：在产生CANCELLED状态节点的时候，先断开的是Next指针，Prev指针并未断开，因此也是必须要从后往前遍历才能够遍历完全部的Node
      - 原因3：前面的线程可能已经阻塞了（内核态），调起的成本比较高
  - 唤醒之后，被唤醒的线程则尝试使用CAS获取锁，假设获取锁得到则把头节点给干掉，把自己设置为头节点
### Collections的排序是哪一种？具体是怎么使用的
- java8中，将入参list先转为了数组Object[]，之后利用Arrays.sort进行排序
- java.util.Arrays.useLegacyMergeSort 的系统属性，可以用于控制 sort 方法是否使用旧版本的归并排序算法。
  - 默认关闭
- 使用TimSort排序(一个自适应的、混合的、稳定的排序算法)
  - TimSort排序算法是归并排序算法的优化版
  - 核心在于利用数列中的原始顺序，所以可以提高很多效率。
- Arrays.sort对long、Int、double等基本数据类型排序是采用的是DualPivotQuicksort
  - 小于某个阈值时，采用双轴快排

#### 字节码是如何被JVM读取的？Java一次编译，到处运行的原因是？
- Java 语言的编译器将 Java 代码编译成字节码，而不是机器码。字节码是一种与具体平台无关的中间代码。
- Java 虚拟机（JVM）是运行 Java 字节码的虚拟机。
  - JVM 有针对不同系统的特定实现（Windows，Linux，macOS），目的是使用相同的字节码，它们都会给出相同的结果。
  - 字节码和不同系统的 JVM 实现是 Java 语言“一次编译，到处运行”的关键所在。
- 如何读取
  - 当执行 Java 程序时，JVM 首先搜索已经加载过的类，如果找不到该类，则会去系统的类路径下查找是否有该类的 .class 文件，然后进行加载、链接和初始化。
  - 在 JVM 中，字节码文件的加载过程由类加载器完成。
  - 类加载器负责从文件系统或网络中加载字节码文件，并将其转换为 JVM 内部的类对象。
  - 类加载器还负责检查字节码文件的格式是否正确，以及检查该类是否已经被加载过。
  - 如果字节码文件的格式正确且该类没有被加载过，则类加载器会将该字节码文件加载到 JVM 中，并生成一个代表该类的类对象。
  - 这个类对象包含了该类的所有信息，例如类的名称、方法、变量等。

#### 主线程的上下文如何传递给子线程
- InheritableThreadLocal是ThreadLocal的一个子类，它可以在子线程中继承主线程的上下文信息。

### java反射如何实现
- 基于4个类：class（类对象），Constructor（构造器对象，包括无参数和有参数的构造函数），Field，Method
- JVM层面能拿到class文件中的类内容，进而获取它的属性，方法，父类等
- 应用
  - 反射让开发人员可以通过外部类的全路径名创建对象，并使用这些类，实现一些扩展的功能。
  - 反射让开发人员可以枚举出类的全部成员，包括构造函数、属性、方法。以帮助开发者写出正确的代码。
  - 测试时可以利用反射 API 访问类的私有成员，以保证测试代码覆盖率。
  - 反射机制是构建框架技术的基础所在，使用反射可以避免将代码写死在框架中。
    - 举例：
- 性能问题
  - 在反射调用方法的例子中，我们先后调用了Class.forName，Class.getMethod，以及Method.invoke三个操作。其中Class.forName 会调用本地方法，
      - Class.getMethod 会遍历该类的公有方法。如果没有匹配到它还会遍历父级的公有方法，可以知道这两个操作非常耗费时间。
  - Method.invoke 内部有两种实现，一个是 Native 版本，一个是 Java 版本；开始native版本，超过15次后编译成机器码，用java版本。
      - Inflation 机制：java版本的生成需要比native长3倍，但生成之后，要比java的生成快20倍
  - 需要检查方法可见性
  - 需要校验参数
  - 反射方法难以内联
  - 因为动态加载的类型，所以无法进行JIT优化












