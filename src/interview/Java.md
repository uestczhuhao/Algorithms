
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


### hashmap红黑树阈值为8原因：
随机hashCode下，转化为红黑树的概率服从泊松分布，阈值为8时概率为0.00000006


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


### OOM如何排查？
1. 原因
    - 分配的少了：比如虚拟机本身可使用的内存（一般通过启动时的VM参数指定）太少。
    - 应用用的太多，并且用完没释放，浪费了。此时就会造成内存泄露或者内存溢出。
2. 举例
    - java.lang.OutOfMemoryError: Java heap space ------>java堆内存溢出，此种情况最常见，一般由于内存泄露或者堆的大小设置不当引起。对于内存泄露，需要通过内存监控软件查找程序中的泄露代码，而堆大小可以通过虚拟机参数-Xms（最小），-Xmx等修改。
    - java.lang.OutOfMemoryError: PermGen space 或 java.lang.OutOfMemoryError：MetaSpace ------>java方法区，（java8 元空间）溢出了，一般出现于大量Class或者jsp页面，或者采用cglib等反射机制的情况，因为上述情况会产生大量的Class信息存储于方法区。此种情况可以通过更改方法区的大小来解决，使用类似-XX:PermSize=64m -XX:MaxPermSize=256m的形式修改。另外，过多的常量尤其是字符串也会导致方法区溢出。
    - java.lang.StackOverflowError ------> 不会抛OOM error，但也是比较常见的Java内存溢出。JAVA虚拟机栈溢出，一般是由于程序中存在死循环或者深度递归调用造成的，栈大小设置太小也会出现此种溢出。可以通过虚拟机参数-Xss来设置栈的大小。

### 自己实现一个阻塞队列
一个reentrantLock，派生两个condition，一个notEmpty，一个notFull，一个object数组存放元素，一个size标识队列规模（实际的元素个数，非最大值），一个head和tail，标识头尾元素的下标
1. 初始化：object数组初始化为size大小
2. put方法：先lock.lock()，当size == object数组的len时，代表队列满，notFull.await()，直到被唤醒；放置元素在tail位置，tail+1，size++；然后notEmpty.signal()，唤醒可能在等素的线程；最后在finally块中释放锁
3. take方法：先lock.lock()，当size == 0时，代表队列空，notEmpty.await()，直到被唤醒；取出head处元素，强转为E；head++，size++；然后notFull.signal()，唤醒可能在等素的线程；最后在finally块中释放锁
4. 注意：当head或tail为数组长度时，要及时更新为0

### 多线程交替打印
1. LockSupport.park()和LockSupport.unpark(thread)方法，每次线程打印后调用park方法挂起自己，同时unpark另一个线程
2. synchronized方法，同一把锁，每个线程打印后，notify另一个，自己wait；另一个线程被唤醒后，打印，再notify，然后自己wait
3. 阻塞队列（长度为1的两个ArrayBlockingQueue，或两个SynchronousQueue），线程从一个队列中取，同时往另一个队列塞，队列为空则自动阻塞
4. 不使用锁，用一个AtomicInteger，一个线程在其为偶数时打印，一个奇数打印，打印完++
5. ReentrantLock和Condition，每个线程先打印，然后调用condition的signal，唤醒另一个线程；再调用wait，自己挂起，等待被唤醒


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

### 频繁GC
1. 可能是内存泄漏，内存使用完了没释放；jmap（jmap -histo  pid）看对象的存活，哪些对象数太多
2. 内存设置问题，jstat看看，根据业务，新生代、老年代和永久代的设置情况

### future，futureTask的区别
future是个接口，不同的实现是不一样的，常见的是FutureTask（线程池提交Callback）是直接依赖LockSupport.park(nanos)/unpark的，Lock锁/AQS也是依赖这个。get时会检查有没有完成，没完成会进入阻塞，等到任务的流程跑完后，会塞入结果，然后唤醒等待的线程。另一个常见的是CompletableFuture，get的时候的特点是先自旋一定次数，尝试获取结果，拿不到再进入阻塞

### select poll和epoll的区别
select和poll差不多，一个是数组，一个是链表，所以后者没有数量的限制；epoll多注册了一个ctrl事件监听。套接字是操作系统在管理，所以硬件的中断反馈给操作系统，进程从操作系统读取套接字的fd，所以有一个内存拷贝的过程，select和poll是轮询每个套接字，每次都要全部拷贝，而epoll是在初始化时拷贝，每次事件触发时直接响应，不需要再复制


### 线上问题排查
1. jstack（1.5）生成thread dump，记录JVM在某一时刻各个线程执行的情况，是一个文本文件
    - 需要在多个时间段提出多个 Thread Dump信息，然后综合进行对比分析，单独分析一个文件是没有意义的。
2. jstat命令用于见识虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、jit编译等运行数据
3. "VM Thread" 是 JVM 自身启动的一个线程, 它主要用来协调其它线程达到安全点(Safepoint)
4. jmap生成Heap Dump文件
    - jhat 是JDK自带的用于分析JVM Heap Dump文件的工具，使用下面的命令可以将堆文件的分析结果以HTML网页的形式进行展示：jhat <heap-dump-file>



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


### java反射如何实现
基于4个类：class（类对象），Constructor（构造器对象，包括无参数和有参数的构造函数），Field，Method
JVM层面能拿到class文件中的类内容，进而获取它的属性，方法，父类等


### Java对异常的设计以及为何这么设计
1. error：Error是程序无法处理的错误，它是由JVM产生和抛出的，比如OutOfMemoryError、ThreadDeath等。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止。
2. exception：Exception是程序本身可以处理的异常，这种异常分两大类运行时异常和非运行时异常。程序中应当尽可能去处理这些异常。
    - RuntimeException：运行时异常都是RuntimeException类及其子类异常，如NullPointerException、IndexOutOfBoundsException等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。
    - 受检异常：非运行时异常是RuntimeException以外的异常，类型上都属于Exception类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。


### spring的注解
- @Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理
- @Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能
- @Repository注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。
- @Controller层是spring-mvc的注解，具有将请求进行转发，重定向的功能。
- @Service层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。
- 用这些注解对应用进行分层之后，就能将请求处理，业务逻辑处理，数据库操作处理分离出来，为代码解耦，也方便了以后项目的维护和开发。


### spring事物传播行为（事物传播级别）7种
- PROPAGATION_REQUIRED	如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
- PROPAGATION_SUPPORTS	支持当前事务，如果当前没有事务，就以非事务方式执行。
- PROPAGATION_MANDATORY	使用当前的事务，如果当前没有事务，就抛出异常。
- PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起。
- PROPAGATION_NOT_SUPPORTED	以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
- PROPAGATION_NEVER	以非事务方式执行，如果当前存在事务，则抛出异常。
- PROPAGATION_NESTED	如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。

### spring mvc从request到controller的过程
1. 请求打到DispatcherServlet
2. 处理器映射
    - SpringMVC在初始化的时候加入的各种处理器，对于请求到Controller的映射，比较重要的是HandlerMapping和HandlerAdapter，HandlerMapping是用来查找处理请求的对象，HandlerAdaptor是用来处理请求参数
3. 对应的控制器处理
4. 对应的model
5. 返回对应的view给视图解析器
6. 返回视图给用户

### volatile用处，如何实现的
- synchronized 关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，而 volatile 关键字在某些情况下性能要优于 synchronized ，但是要注意 volatile 关键字是无法替代 synchronized 关键字的，因为 volatile 关键字无法保证操作的原子性。通常来说，使用 volatile 必须具备以下 2 个条件：
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
    - synchronized既能保证可见性，又能保证原子性，而volatile只能保证可见性，无法保证原子性。
    - volatile本身不保证获取和设置操作的原子性，仅仅保持修改的可见性。但是java的内存模型保证声明为volatile的long和double变量的get和set操作是原子的。
- 使用场景
    - 作为状态标识，一般来说状态值的改变都是完全独立于上一个状态的
    - 多线程初始化对象，一个线程赋值，多个线程使用
    - 对象的属性，由一个线程修改，其他线程立即可见
    - 高性能读-写锁策略，volatile保证读可见性，锁保证赋值操作的原子性

### 线程池锁回收空闲线程
- 超过corePoolSize的空闲线程由线程池回收，线程池Worker启动跑第一个任务之后就一直循环遍历线程池任务队列，超过指定超时时间获取不到任务就remove Worker，最后由垃圾回收器回收。
- 另外，线程池在提交任务时新建核心线程（若需要）并执行task

### 线程池关闭
- shutdown：启动有序关闭，其中执行先前提交的任务，但不会接受新任务。
    - 一定要确保任务里不会有永久阻塞等待的逻辑，否则线程池就关闭不了。
- shutdownNow：尝试终止所有正在执行的任务，并停止处理等待队列中的的任务，最后将所有未执行的任务列表的形式返回，此方法会将任务队列中的任务移除并以列表形式返回。此方法不会等待正在执行的任务执行完毕，要等待任务执行完毕可以使用awaitTermination()方法
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
    - CachedThreadPool：核心0，最大int最大值，队列采用SynchronousQueue，不存储任务，有新任务就起线程运行之，keepAliveTime为60s
    - SingleThreadExecutor：单线程池，max和core都为1，keepAliveTime为0，队列无界
    - ScheduledThreadPool：处理延时任务或定时任务，队列为DelayQueue（无界队列），内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
    - newWorkStealingPool：基于ForkJoinPool，不是ThreadPoolExecutor，使用所有可用处理器作为目标并行度，创建一个窃取线程的池

### G1回收器
特点：高吞吐、没有内存碎片、收集时间可控
1. 把堆划分为大小相等的Region（2048个），保留新生代和老年代，但不再物理隔离
2. 每次回收价值最大的Region（价值：回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的回收时间，优先回收价值最大的
3. 在回收一个Region的时候不需要执行全堆扫描，只需要检查它的RS（Remembered Set，记录外部指向本Region的所有引用）就可以找到外部引用，而这些引用就是initial mark的根之一
4. G1还有一个及其重要的特性：软实时（soft real-time）。所谓的实时垃圾回收，是指在要求的时间内完成垃圾回收。“软实时”则是指，用户可以指定垃圾回收时间的限时，G1会努力在这个时限内完成垃圾回收，但是G1并不担保每次都能在这个时限内完成垃圾回收。
5. 回收过程
    - 初始标记(Initial Marking)：这个阶段是STW(Stop the World )的，所有应用线程会被暂停，标记出从GC Root开始直接可达的对象。（触发一次年轻代GC）
    - 并发标记：从GC Roots开始对堆中对象进行可达性分析，找出存活对象，耗时较长。
    - 最终标记(Final Marking)：标记那些在并发标记阶段发生变化的对象，将被回收。
    - 独占清理(cleanup,STW): 计算各个区域的存活对象和GC回收比例,并进行排序，识别可以混合回收的区域。为下阶段做铺垫。是STW的。
    - 并发清理阶段: 识别并清理完全空闲的区域。
6. GC模式
    - YoungGC年轻代收集：在分配一般对象（非巨型对象）时，当所有eden region使用达到最大阀值并且无法申请足够内存时，会触发一次YoungGC。每次younggc会回收所有Eden以及Survivor区，并且将存活对象复制到Old区以及另一部分的Survivor区。
    - mixed gc：当越来越多的对象晋升到老年代old region时，为了避免堆内存被耗尽，虚拟机会触发一个混合的垃圾收集器，即mixed gc，该算法并不是一个old gc，除了回收整个young region，还会回收一部分的old region，这里需要注意：是一部分老年代，而不是全部老年代，可以选择哪些old region进行收集，从而可以对垃圾回收的耗时时间进行控制。（用的是5的回收过程）
    - G1没有fullGC概念，需要fullGC时，调用serialOldGC进行全堆扫描（包括eden、survivor、o、perm）。
7. young GC过程
   年轻代垃圾回收只会回收Eden区和Survivor区。
- 扫描根 -> 更新RSet -> 处理RSet -> 复制对象（如果Survivor空间不够,Eden空间的部分数据会直接晋升到老年代空间） -> 处理引用

### 引用类型
- 强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。
- 如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。
- 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
- “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。（虚引用主要用来跟踪对象被垃圾回收器回收的活动。）


### ThreadLocal内存泄漏
- ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreadLocalMap -> Entry -> value永远无法回收，造成内存泄漏。
- 其实，ThreadLocalMap的设计中已经考虑到这种情况，也加上了一些防护措施：在ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value。
- 但是这些被动的预防措施并不能保证不会内存泄漏：
    - 使用线程池的时候，这个线程执行任务结束，ThreadLocal对象被回收了，线程放回线程池中不销毁，这个线程一直不被使用，导致内存泄漏。
    - 分配使用了ThreadLocal又不再调用get(),set(),remove()方法，那么这个期间就会发生内存泄漏。
    - 每次使用完ThreadLocal，都调用它的remove()方法，清除数据。

### Spring Boot Starter怎么实现？如何自定义Starter
利用starter实现自动化配置只需要两个条件——maven依赖、配置文件

### Spring循环依赖
1. 构造器循环依赖
    - 通过构造器注入构成的循环依赖，此依赖是无法解决的，只能抛出BeanCurrentlyInCreationException异常表示循环依赖
    - 原理：Spring容器将每一个正在创建的bean标识符放在一个“当前创建bean池”中，bean标识符创建过程中将一直保持在这个池中，因为如果在创建bean过程中发现自己已经在“当前创建bean池”中时，将会抛出BeanCurrentlyInCreationException异常表示循环依赖；而对于创建完毕的bean将从“当前创建bean池”中清除掉。
2. prototype范围的依赖处理
- 对于scope为prototype范围的bean，Spring容器无法完成依赖注入，因为Spring容器不进行缓存“prototype”作用域的bean，因此无法提前暴露一个创建中的bean，所以检测到循环依赖会直接抛出BeanCurrentlyInCreationException异常
3. Setter方法注入
    - 一级缓存：用于存放完全初始化好的 bean
    - 二级缓存：存放原始的 bean 对象（尚未填充属性），用于解决循环依赖
    - 三级级缓存：存放 bean 工厂对象
    - 过程
        - A 创建过程中需要 B， 于是 A 将自己放到三级缓存里面，去实例化 B
        - B 实例化的时候发现需要 A，于是 B 先查一级缓存，没有，再查二级缓存，还是没有，再查三级缓存找到了A，然后把三级缓存中的 A 放到二级缓存，并删除三级缓存中的 A
        - B 顺利初始化完毕，将自己放到一级缓存中(此时 B 中的 A 还是创建中状态，并没有完全初始化)，删除三级缓存中的 B然后接着回来创建 A，此时 B 已经完成创建，直接从一级缓存中拿到 B，完成 A 的创建，并将 A 添加到单例池，删除二级缓存中的 A


### FactoryBean和BeanFactory区别
- BeanFactory，以Factory结尾，表示它是一个工厂类(接口)， 它负责生产和管理bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。（如XMLBeanFactory）
- 以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。
    - 一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在<bean>中提供大量的配置信息。配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
    - Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。它们隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式。


### thread有几种状态，和操作系统的对应关系
1. java线程状态
    - NEW
    - RUNNABLE：对应操作系统的READY和RUNNING
    - BLOCKED
    - WAITING
    - TIMED_WAITING
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

### 动态代理
1. JDK动态代理：利用反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
2. CGlib动态代理（Enhancer增强类，把代理类设置为其父类）：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
3. 区别
    - JDK代理使用的是反射机制实现aop的动态代理，CGLIB代理使用字节码处理框架asm，通过修改字节码生成子类。所以jdk动态代理的方式创建代理对象效率较高，执行效率较低，cglib创建效率较低，执行效率高；
    - JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托handler去调用原始实现类方法，CGLIB则使用的继承机制，具体说被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的，如果被代理类有接口，那么代理类也可以赋值给接口。

### springboot 的启动
1. SpringBootApplication注解
    - @SpringBootConfiguration // 继承了Configuration，表示当前是注解类
    - @EnableAutoConfiguration // 开启springboot的注解功能，springboot的四大神器（auto-configuration、starters、cli、actuator）之一，其借助@import的帮助，从各个spring.factories配置文件中加载需要的bean到IOC容器
        - 通过@Import(AutoConfigurationImportSelector.class)，从classpath中搜寻所有的META-INF/spring.factories配置文件，并将其中org.springframework.boot.autoconfigure.EnableAutoConfiguration对应的配置项通过反射（Java Refletion）实例化为对应的标注了@Configuration的JavaConfig形式的IoC容器配置类，然后汇总为一个并加载到IoC容器。
    - @ComponentScan // 扫描路径设置
2. 启动过程
    - 从spring.factories配置文件中加载EventPublishingRunListener对象，该对象拥有SimpleApplicationEventMulticaster属性，即在SpringBoot启动过程的不同阶段用来发射内置的生命周期事件;
    - 准备环境变量，包括系统变量，环境变量，命令行参数，默认变量，servlet相关配置变量，随机值以及配置文件（比如application.properties）等;
    - 控制台打印SpringBoot的bannner标志；
    - 根据不同类型环境创建不同类型的applicationcontext容器，因为这里是servlet环境，所以创建的是AnnotationConfigServletWebServerApplicationContext容器对象；
    - 从spring.factories配置文件中加载FailureAnalyzers对象,用来报告SpringBoot启动过程中的异常；
    - 为刚创建的容器对象做一些初始化工作，准备一些容器属性值等，对ApplicationContext应用一些相关的后置处理和调用各个ApplicationContextInitializer的初始化方法来执行一些初始化逻辑等，其中最核心的一步，将之前通过@EnableAutoConfiguration获取的所有配置以及其他形式的IoC容器配置加载到已经准备完毕的ApplicationContext。
    - 刷新容器，这一步至关重要。比如调用bean factory的后置处理器，注册BeanPostProcessor后置处理器，初始化事件广播器且广播事件，初始化剩下的单例bean和SpringBoot创建内嵌的Tomcat服务器等等重要且复杂的逻辑都在这里实现，主要步骤可见代码的注释，关于这里的逻辑会在以后的spring源码分析专题详细分析；
    - 执行刷新容器后的后置处理逻辑，注意这里为空方法；
    - 调用ApplicationRunner和CommandLineRunner的run方法，我们实现这两个接口可以在spring容器启动后需要的一些东西比如加载一些业务数据等;
    - 报告启动异常，即若启动过程中抛出异常，此时用FailureAnalyzers来报告异常;
    - 最终返回容器对象，这里调用方法没有声明对象来接收。


#### BeanFactory 和ApplicationContext的区别
BeanFactory和ApplicationContext都是接口，并且ApplicationContext（即为IOC容器）间接继承了BeanFactory。
- BeanFactory是Spring中最底层的接口，提供了最简单的容器的功能，只提供了实例化对象和获取对象的功能
- ApplicationContext是Spring的一个更高级的容器，提供了更多的有用的功能。例如：获取Bean的详细信息(如定义、类型)、国际化的功能、统一加载资源的功能、强大的事件机制、对Web应用的支持



### springboot 四大神器
- actuator：Spring Boot Actuator的关键特性是在应用程序里提供众多Web端点，通过它们了解应用程序 运行时的内部状况。
- starters：构建Spring Boot启动器是为了解决复杂的依赖管理。Starter POM是一组方便的依赖描述符，您可以在应用程序中包含这些描述符。
    - 创建一个ConfigurationProperties用于保存你的配置信息（如果你的项目不使用配置信息则可以跳过这一步，不过这种情况非常少见）
    - 配置spring.factories配置文件；在spring boot启动时会加载各个starter的配置文件，汇总成一个配置类，加载到IOC容器
- CLI：可以检测到代码中使用的类，知道需要给Classpath中添加的哪些起步依赖才能让程序运行起来。
- auto-configuration：Spring Boot自动配置代表了一种基于类路径上存在的依赖关系自动配置Spring应用程序的方法。还可以通过定义消除自动配置类中包含的某些bean。这些可以使开发更快更容易。

### CompletableFuture
#### Future的局限
- 不能手动完成
- Future 的结果在非阻塞的情况下，不能执行更进一步的操作，必须调用get阻塞等待
- 多个 Future 不能串联在一起组成链式调用
- 不能组合多个 Future 的结果
- 没有异常处理
- 
#### 解决
- 使用CompletableFuture.complete() 手工的完成一个 Future
- 使用 runAsync() 运行异步计算或 使用 supplyAsync() 运行一个异步任务并且返回结果
- 可以使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture
- 使用 thenCompose() /thenCombine() 组合两个独立的future，前者用于当一个future依赖另外一个future的时候用来组合两个future，后者用于当两个独立的Future都完成的时候，用来做一些事情（还有allOf()/ anyOf() 可以使用）
- 使用 exceptionally() 回调处理异常 / 使用 handle() 方法处理异常


### 实现lru
一个map（键为元素的值，值为node本身），内部放node，实现O(1)时间内判断是否在集合中；节点采用双端队列，方便删除、新增节点；
1. get：首先判断map中是否存在元素，不存在返回-1；存在则从map中get节点，把节点move2Head，返回节点的值即可
2. put：首先判断map中是否存在，不存在则新建节点并添加链表的头，并将其添加到map；存在则更新map中的值，并把节点move2Head
- 注意当判定size >= capacity时，要找到tail的前置节点curNode，remove map中的节点，并删除curNode
3. 私有方法
- deleteNode：找到pre和next节点，相互连接，把curNode的pre和next置为null，size--
- addHeadNode：处理前置哨兵head，让head和curNode相连，原先的head.next连接到curNode的后面
- move2Head：先deleteNode，再add2Head


#### 性能问题
在反射调用方法的例子中，我们先后调用了Class.forName，Class.getMethod，以及Method.invoke三个操作。其中Class.forName 会调用本地方法，Class.getMethod 会遍历该类的公有方法。如果没有匹配到它还会遍历父级的公有方法，可以知道这两个操作非常耗费时间。
- Method.invoke 内部有两种实现，一个是 Native 版本，一个是 Java 版本；开始native版本，超过15次后编译成机器码，用java版本。Inflation 机制：java版本的生成需要比native长3倍，但生成之后，要比java的生成快20倍
- 需要检查方法可见性
- 需要校验参数
- 反射方法难以内联
- 因为动态加载的类型，所以无法进行JIT优化


#### 应用
- 反射让开发人员可以通过外部类的全路径名创建对象，并使用这些类，实现一些扩展的功能。
- 反射让开发人员可以枚举出类的全部成员，包括构造函数、属性、方法。以帮助开发者写出正确的代码。
- 测试时可以利用反射 API 访问类的私有成员，以保证测试代码覆盖率。
- 反射机制是构建框架技术的基础所在，使用反射可以避免将代码写死在框架中。
