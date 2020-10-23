#### JVM类加载器

* 类的声明周期

  ![image-20201021230819966](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201021230819966.png)

* **类的加载时机**

  * 当虚拟机启动时，初始化用户指定的主类，就是启动执行的 main 方法所在的类；
  * 当遇到用以新建目标类实例的 new 指令时，初始化 new 指令的目标类，就是 new一个类的时候要初始化；
  * 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
  * 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
  * 子类的初始化会触发父类的初始化；
  * 如果一个接口定义了 default 方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化；
  * 使用反射 API 对某个类进行反射调用时，初始化这个类，其实跟前面一样，反射调用要么是已经有实例了，要么是静态方法，都需要初始化；
  * 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。

* **不会初始化（可能会加载）**

  * 通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。
  * 定义对象数组，不会触发该类的初始化。
  * 常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。
  * 通过类名获取 Class 对象，不会触发类的初始化，Hello.class 不会让 Hello 类初始化
  * 通过 Class.forName 加载指定类时，如果指定参数 initialize 为 false 时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。Class.forName（“jvm.Hello”）默认会加载 Hello 类。
  * 通过 ClassLoader 默认的 loadClass 方法，也不会触发初始化动作（加载了，但是不初始化）

* **三类加载器**

  * 启动类加载器（BootstrapClassLoader）
  * 扩展类加载器（ExtClassLoader）
  * 应用类加载器（AppClassLoader）

#### JVM内存结构

* 方法中使用的**原生数据类型**和**对象引用地址**在**栈上**存储；**对象**、**对象成员与类定义、静态变量**在**堆上**。
* 堆内存又称为“共享堆”，堆中的所有对象，可以被所有线程访问, 只要他们能拿到对象的引用地址。
* 如果一个线程可以访问某个对象时，也就可以访问该对象的成员变量
* 如果两个线程同时调用某个对象的同一方法，则它们都可以访问到这个对象的成员变量，但每个线程的局部变量副本是独立的。

### JDK内置命令笔记

* **java**：Java 应用的启动程序

  * 例子： java -jar  gateway-server-0.0.1-SNAPSHOT.jar

*  **javac** : JDK 内置的编译工具

* **javap** : 反编译 class 文件的工具

* **javadoc** : 根据 Java 代码和标准注释,自动生成相关的API说明文档

* **javah** : JNI 开发时, 根据 java 代码生成需要的 .h文件。

* **extcheck** : 检查某个 jar 文件和运行时扩展 jar 有没有版本冲突，很少使用

* **jdb** :Java Debugger ; 可以调试本地和远端程序, 属于 JPDA 中的一个 demo 实现, 供其他调试器参考。开发时很少使用

* **jdeps** : 探测 class 或 jar 包需要的依赖

* **jar** : 打包工具，可以将文件和目录打包成为 .jar 文件；.jar 文件本质上就是 zip 文件, 只是后缀不同。使用时按顺序对应好选项和参数即可。

* **keytool** : 安全证书和密钥的管理工具; （支持生成、导入、导出等操作）

* **jarsigner** : JAR 文件签名和验证工具

* **policytool** : 实际上这是一款图形界面工具, 管理本机的 Java 安全策略

* **jps/jinfo** : 查看 java 进程

  * ![image-20201019214532353](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019214532353.png)

    

* **jstat**:  查看 jvm 内部 gc 相关信息

  * -class 类加载(Class loader)信息统计. 

  * -compiler JIT 即时编译器相关的统计信息。

  * -gc GC 相关的堆内存信息. 用法: jstat -gc -h 10 -t 864 1s 20

    ![image-20201019225114529](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019225114529.png)

    * **Timestamp** ：JVM启动时长
    * **S0C**：0号存活区的当前容量（capacity），单位KB
    * **S1C**：1号存活区的当前容量，单位KB
    * **S0U**：0号存活区的使用量（utilization），单位KB
    * **S1U**：1号存活区的使用量，单位KB
    * **EC** ：Eden区，新生代的当前容量，单位KB
    * S1C：Eden区，新生代的使用量，单位KB
    * **OC** ：Old区，老年代的当前容量，单位KB
    * **OU** ：Old区，老年代的使用量，单位KB（**需重点关注**）
    * **MC** ：元数据的容量，单位KB
    * **MU** ：元数据的使用量，单位KB
    * **CCSC** ：压缩的class的空间容量，单位KB
    * **CCSU** ：压缩的class空间使用量，单位KB
    * **YGC** ：年轻代GC的次数
    * **YGCT**：年轻代GC消耗的总时间（**需重点关注**）
    * **FGC** ：Full Gc的次数
    * **GFCT** ：Full GC 消耗的时间（**需重点关注**）
    * **GCT** ：垃圾收集消耗的总时间

  * -gccapacity 各个内存池分代空间的容量。

  *  -gccause 看上次 GC, 本次 GC（如果正在GC中）的原因, 其他 

  * 输出和 -gcutil 选项一致。

  * -gcnew 年轻代的统计信息. （New = Young = Eden + S0 + S1） 

  * -gcnewcapacity 年轻代空间大小统计. 

  * -gcold 老年代和元数据区的行为统计。

  * -gcoldcapacity old 空间大小统计. 

  * -gcmetacapacity meta 区大小统计. 

  * -gcutil GC 相关区域的使用率(utilization)统计。

    * jstat -gcutil pid 1000 1000 

      ![image-20201019224458879](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019224458879.png)

    * **Timestamp** ：JVM启动时长

    * **S0** ：就是0号存活区的百分比使用率.0%很正常,因为S0和S1随时一个是空的。

    * **S1**： 就是1号存活区的百分比使用率

    * **E**：就是Eden区，新生代的百分比使用率。

    * **O**：就是Old区，老年代百分比使用率。

    * **M**：就是Meta区，元数据区百分比使用率。

    * **CCS**： 压缩class空间（Compressed class space）的百分比使用率。

    * **YGC**： （Yong GC） 年轻代GC的数。

    * **YGCT**： 年轻代GC消耗的总时间。

    * **FGC**： FullGC的次数。

    * **FGCT**： FullGc的总时间。

    * **GCT**： 所有GC加起来消耗的总时间。

  * -printcompilation 打印 JVM 编译统计信息。

* **jmap** : 查看 heap 或类占用空间统计，常用选项为3个

  * -heap： 打印堆内存（/内存池）的配置和使用信息

    ![image-20201019230459520](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019230459520.png)

  * -histo 看哪些类占用的空间最多, 直方图

    ![image-20201019230946233](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019230946233.png)

  * -dump:format=b,file=xxxx.hprof Dump 堆内存

    ![image-20201019231252156](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019231252156.png)



* **jstack** ：查看线程信息

  * **-F** 强制执行 thread dump. 可在 Java 进程卡死（hung 住）时使用, 此选项可能需要系统权限。

  * -m 混合模式(mixed mode),将 Java 帧和 native帧一起输出, 此选项可能需要系统权限。

  * **-l** 长列表模式. 将线程相关的 locks 信息一起输出，比如持有的锁，等待的锁。

    ![image-20201019231705631](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019231705631.png)



* **jcmd** ：执行 jvm 相关分析命令（整合命令）

  * ![image-20201019232001293](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019232001293.png)

  * 示例  jcmd 16776 GC.heap_info

    ![image-20201019231920208](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201019231920208.png)



* **jrunscript/jjs** ：执行 js 命令
  * **当curl命令用**：jrunscript -e "cat('http://www.baidu.com')"
  * 执行js脚本片段：jrunscript -e "print('hello,kk.jvm'+1)"
  * **执行js文件**：jrunscript -l js -f /XXX/XXX/test.js
* **jconsole**
  * **待整理**
* **jvisualvm**
  * **待整理**

* **VisualGC**
  * **待整理**
* **jmc**
  * **待整理**



### GC 的背景与一般原理

* 为什么会有 GC

  本质上是内存资源的有限性，因此需要大家共享使用，手工申请，手动释放。

* **标记清除算法**（Mark and Sweep） 

  * Marking（标记）: 遍历所有的可达对象，并在本地内存(native)中分门别类记下。
  *  Sweeping（清除）: 这一步保证了，不可达对象所占用的内存，在之后进行内存分配时可以重用。

* 并行 GC 和 CMS 的基本原理。
    * 优势：可以处理循环依赖，只扫描部分对象
    * 除了清除，还要做压缩。
    
* 怎么才能标记和清除清楚上百万对象呢？
  
  * 答案就是 STW，让全世界停止下来。
  
* GC内存对象在内存池之间转移

  * 对象分配在新生代的 Eden 区，

  * 标记阶段 Eden 区存活的对象就会复制到存活区；

  * **注意：为什么是复制，不是移动？？？大家想想**

  * 两个存活区 from 和 to，互换角色。对象存活到一定周期会提升到老年代。

    * 由如下参数控制提升阈值

      -XX:+MaxTenuringThreshold=15
    
  * 老年代默认都是存活对象，采用移动方式：

      1. 标记所有通过 GC roots 可达的对象；
      2. 删除所有不可达对象；
      3. 整理老年代空间中的内容，方法是将所有的存活对象复制，从老年代空间开始的地方依次存放。持久代/元数据区
  * 持久代/元数据区
    * 1.8之前 -XX:MaxPermSize=256m
    * 1.8之后 -XX:MaxMetaspaceSize=256m

* 可以作为 GC Roots 的对象

    1. 当前正在执行的方法里的局部变量和输入参数

    2. 活动线程（Active threads）

    3. 所有类的静态字段（static field）

    4. JNI 引用
    
* 此阶段暂停的时间，与堆内存大小,对象的总数没有直接关系，而是由存活对象（alive objects）的数量来决定。所以增加堆内存的大小并不会直接影响标记阶段占用的时间。

#### 串行GC（Serial GC）/ParNewGC

* -XX:+UseSerialGC 配置串行 GC

* 串行 GC 对年轻代使用 mark-copy（标记-复制） 算法

* 对老年代使用 mark-sweep-compact（标记-清除-整理）算法。

* 两者都是单线程的垃圾收集器，不能进行并行处理，所以都会触发全线暂停（STW），停止所有的应用线程。

  * 因此这种 GC 算法不能充分利用多核 CPU。不管有多少 CPU 内核，JVM 在垃圾收集时都只能使用单个核心。

  * CPU 利用率高，暂停时间长。简单粗暴，就像老式的电脑，动不动就卡死。
  
* 该选项只适合几百 MB 堆内存的 JVM，而且是单核 CPU 时比较有用。

  **想想 why？** 
  
  * 效率
  
* -XX:+USeParNewGC 改进版本的 Serial GC，可以配合 CMS 使用。

#### **并行** GC（Parallel GC） 

* **注：JDK8 及以前默认的垃圾收集器**
* -XX：+UseParallelGC
* -XX：+UseParallelOldGC
* -XX：+UseParallelGC -XX:+UseParallelOldGC
* 年轻代和老年代的垃圾回收都会触发 STW 事件。
* 在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。 
* -XX：ParallelGCThreads=N 来指定 GC 线程数， 其默认值为 CPU 核心数。
* 并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量: 
* 在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短；
* 在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源

#### CMS G（Mostly Concurrent Mark and Sweep Garbage Collector）

* **-XX：+UseConcMarkSweepGC**

* 其对年轻代采用并行 STW 方式的 **mark-copy (标记-复制)**算法，对老年代主要使用并发 **mark-sweep (标记-清除)**算法。

* CMS GC 的设计目标是避免在老年代垃圾收集时出现长时间的卡顿，主要通过两种手段来达成此目标：

  *  不对老年代进行整理，而是使用空闲列表（free-lists）来管理内存空间的回收。

  * 在 mark-and-sweep （标记-清除） 阶段的大部分工作和应用线程一起并发执行。

  * 也就是说，在这些阶段并没有明显的应用线程暂停。但值得注意的是，它仍然和应用线程争抢CPU 时间。

    默认情况下，CMS 使用的并发线程数等于 CPU 核心数的 1/4。

  * 如果服务器是多核 CPU，并且主要调优目标是降低GC停顿导致的系统延迟，那么使用 CMS 是

  个很明智的选择。

  * 进行老年代的并发回收时，可能会伴随着多次年轻代的 minor GC。

  **思考：并行 Parallel 与并发 Concurrent 的区别？**

* 六个阶段

  * **阶段 1: Initial Mark（初始标记）**

    * 这个阶段伴随着**STW暂停**。初始标记的目标是标记所有的根对象，包括根对象直接引用的对象，以及被年轻代中

      所有存活对象所引用的对象（老年代单独回收）。

  * **阶段 2: Concurrent Mark(并发标记)**

    * 在此阶段，CMS GC遍历老年代，标记所有的存活对象，从前一阶段Initial Mark找到的根对象开始算起。
    * 并发标记阶段，就是与应用程序同时运行，不用暂停的阶段。
    
  * **阶段 3: Concurrent Preclean（并发预清理）**

    * 此阶段同样是与应用线程并发执行的，不需要停止应用线程。
    *  因为前一阶段【并发标记】与程序并发运行，可能有一些引用关系已经发生了改变。
    * 如果在并发标记过程中引用关系发生了变化，JVM 会通过**Card（卡片）**的方式将发生了改变的区域标记为脏区，
    * 这就是所谓的 **卡片标记**（Card Marking）。

  * **阶段 4: Final Remark（最终标记）**

    * 最终标记阶段是此次GC事件中的第二次（也是最后一次）**STW** 停顿。本阶段的目标是完成老年代中所有存活对象的标记.
    * 因为之前的预清理阶段是并发执行的，有可能 GC 线程跟不上应用程序的修改速度。所以需要一次STW 暂停来处理各种复杂的情况。
    * 通常 CMS 会尝试在年轻代尽可能空的情况下执行 FinalRemark 阶段，以免连续触发多次STW 事件。

  * **阶段 5: Concurrent Sweep（并发清除）**

    * 此阶段与应用程序并发执行，不需要STW停顿。JVM在此阶段删除不再使用的对象，并回收他们占用的内存空间。
    
  * **阶段 6: Concurrent Reset（并发重置）**
    
    * 此阶段与应用程序并发执行，重置CMS算法相关的内部数据，为下一次GC循环做准备。
    
  * **CMS GC 缺点**
    
  * CMS垃圾收集器在减少停顿时间上做了很多复杂而有用的工作，用于垃圾回收的并发线程执行的同时，并不需要暂停应用线程。 当然，CMS也有一些缺点，其中最大的问题就是老年
    代内存碎片问题（因为不压缩），在某些情况下GC会造成不可预测的暂停时间，特别是堆内存较大的情况下。
    
#### **G1 GC**

* 定义

  * G1的全称是Garbage-First，意为垃圾优先，哪一块的垃圾最多就优先清理它。
  * G1 GC最主要的设计目标是：将STW停顿的时间和分布，变成可预期且可配置的。
  * 事实上，G1 GC是一款软实时垃圾收集器，可以为其设置某项特定的性能指标。
  * 为了达成可预期停顿时间的指标，G1 GC有一些独特的实现。
  * 首先，堆不再分成年轻代和老年代，而是划分为多个（通常是2048个）可以存放对象的 小块堆区域(smaller heap regions)。每个小块，可能一会被定义成 Eden 区，一会被指定为 Survivor区或者Old区。
  * 在逻辑上，所有的Eden区和Survivor区合起来就是年轻代，所有的Old区拼在一起那就是老年代

* 内存划分

  ![image-20201021205246413](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201021205246413.png)

* 参数

  * -XX:+UseG1GC 
  * -XX:MaxGCPauseMillis=50

* 配置参数

  * -XX:+UseG1GC：启用G1 GC；

  * **-XX:G1NewSizePercent**：初始年轻代占整个Java Heap的大小，默认值为5%； 

  * **-XX:G1MaxNewSizePercent**：最大年轻代占整个Java Heap的大小，默认值为60%； 

  * **-XX:G1HeapRegionSize**：设置每个Region的大小，单位MB，需要为1，2，4，8，16，32中的某个值，

    默认是 堆内存的1/2000。如果这个值设置比较大，那么大对象就可以进入Region了。

  * **-XX:ConcGCThreads**：与Java应用一起执行的GC线程数量，默认是Java线程的1/4，减少这个参数的数值可能会 

    提升并行回收的效率，提高系统内部吞吐量。如果这个数值过低，参与回收垃圾的线程不足，也会导致并行回收机制耗时加长。
    
  * **-XX:+InitiatingHeapOccupancyPercent**（简称IHOP）：G1内部并行回收循环启动的阈值，默认为Java Heap的
  
      **45%**。这个可以理解为老年代使用大于等于**45%**的时候，JVM会启动垃圾回收。这个值非常重要，它决定了在什么 
  
      时间启动老年代的并行回收。
  
  * **-XX:G1HeapWastePercent**：G1停止回收的最小内存大小，默认是堆大小的5%。GC会收集所有的Region中的对 
  
    象，但是如果下降到了5%，就会停下来不再收集了。就是说，不必每次回收就把所有的垃圾都处理完，可以遗留 
  
    少量的下次处理，这样也降低了单次消耗的时间。
  
  * -XX:G1MixedGCCountTarget：设置并行循环之后需要有多少个混合GC启动，默认值是8个。老年代Regions的回 
  
    收时间通常比年轻代的收集时间要长一些。所以如果混合收集器比较多，可以允许G1延长老年代的收集时间。
  
  * -XX:+G1PrintRegionLivenessInfo：这个参数需要和 -XX:+UnlockDiagnosticVMOptions 配合启动，打印JVM的调试信息，每个Region里的对象存活信息。
  
  * -XX:G1ReservePercent：G1为了保留一些空间用于年代之间的提升，默认值是堆空间的10%。因为大量执行回收的地方在年轻代（存活时间较短），所以如果你的应用里面有比较大的堆内存空间、比较多的大对象存活，这里需要保留一些内存。
  
  * -XX:+G1SummarizeRSetStats：这也是一个VM的调试信息。如果启用，会在VM退出的时候打印出RSets的详细总结信息。如果启用-XX:G1SummaryRSetStatsPeriod参数，就会阶段性地打印RSets信息。
  
  * -XX:+G1TraceConcRefinement：这个也是一个VM的调试信息，如果启用，并行回收阶段的日志就会被详细打印出来。
  
  * **-XX:+GCTimeRatio**：这个参数就是计算花在Java应用线程上和花在GC线程上的时间比率，默认是9，跟新生代内存的分配比例一致。这个参数主要的目的是让用户可以控制花在应用上的时间，G1的计算公式是**100/（1+GCTimeRatio）**。这样如果参数设置为9，则最多10%的时间会花在GC工作上面。Parallel GC的默认值是99，表示1%的时间被用在GC上面，这是因为Parallel GC贯穿整个GC，而G1则根据Region来进行划分，不需要全局性扫描整个内存堆。
  
  * -XX:+UseStringDeduplication：手动开启Java String对象的去重工作，这个是JDK8u20版本之后新增的参数，主要用于相同String避免重复申请内存，节约Region的使用。
  
  * **-XX:MaxGCPauseMills**：预期G1每次执行GC操作的暂停时间，单位是毫秒，默认值是200毫秒，G1会尽量保证控制在这个范围内。
  
* 处理步骤

  1. 年轻代模式转移暂停（Evacuation Pause）
     G1 GC会通过前面一段时间的运行情况来不断的调整自己的回收策略和行为，以此来比较稳定地控制暂停时间。
     在应用程序刚启动时，G1还没有采集到什么足够的信息，这时候就处于初始的 fully-young 模式。
     当年轻代空间用满后，应用线程会被暂停，年轻代内存块中的存活对象被拷贝到存活区。
     如果还没有存活区，则任意选择一部分空闲的内存块作为存活区。
     
     **拷贝的过程称为转移(Evacuation)**，这和前面介绍的其他年轻代收集器是一样的工作原理。
     
  2. 并发标记（Concurrent Marking）
     同时我们也可以看到，G1 GC的很多概念建立在CMS的基础上，所以下面的内容需要对CMS有一定的理解。
     G1并发标记的过程与CMS基本上是一样的。G1的并发标记通过 Snapshot-At-The-Beginning(起始快照) 的方式，在标记阶段开始时记下所有的存活对象。即使在标记的同时又有一些变成了垃圾。通过对象的存活信息，可以构建出每个小堆块的存活状态，以便回收集能高效地进行选择。 

     这些信息在接下来的阶段会用来执行老年代区域的垃圾收集。
     有两种情况是可以完全并发执行的：
      一、如果在标记阶段确定某个小堆块中没有存活对象，只包含垃圾；
     二、在STW转移暂停期间，同时包含垃圾和存活对象的老年代小堆块。
     当堆内存的总体使用比例达到一定数值，就会触发并发标记。这个默认比例是 45%，但也可以通过JVM参数 InitiatingHeapOccupancyPercent 来设置。和CMS一样，

     G1的并发标记也是由多个阶段组成，其中一些阶段是完全并发的，还有一些阶段则会暂停应用线程。

     * **阶段**1: Initial Mark(初始标记)
       此阶段标记所有从GC根对象直接可达的对象。
     * **阶段2**: Root Region Scan(Root区扫描)此阶段标记所有从 "根区域" 可达的存活对象。根区域包括：非空的区域，以及在标记过程中不得不收集的区域。
     *  **阶段3**: Concurrent Mark(并发标记)
       此阶段和CMS的并发标记阶段非常类似：只遍历对象图，并在一个特殊的位图中标记能访问到的对象。
     * **阶段4**: Remark(再次标记)
       和CMS类似，这是一次STW停顿(因为不是并发的阶段)，以完成标记过程。 G1收集器会短暂地停止应用线程，停止并发更新信息的写入，处理其中的少量信息，并标记所有在并发标记开始时未被标记的存活对象。
     * **阶段5**: Cleanup(清理)
       最后这个清理阶段为即将到来的转移阶段做准备，统计小堆块中所有存活的对象，并将小堆块进行排序，以提升GC的效率，维护并发标记的内部状态。 所有不包含存活对象的小堆块在此阶段都被回收了。有一部分任务是并发的：例如空堆区的回收，还有大部分的存活率计算。此阶段也需要一个短暂的STW暂停。

  3. 转移暂停: 混合模式（Evacuation Pause (mixed)）
     并发标记完成之后，G1将执行一次混合收集（mixed collection），就是不只清理年轻代，还将一部分老年代区域也加入到 回收集 中。混合模式的转移暂停不一定紧跟并发标记阶段。有很多规则和历史数据会影响混合模式的启动时机。

     比如，假若在老年代中可以并发地腾出很多的小堆块，就没有必要启动混合模式。因此，在并发标记与混合转移暂停之间，很可能会存在多次 young 模式的转移暂停。具体添加到回收集的老年代小堆块的大小及顺序，也是基于许多规则来判定的。其中包括指定的软实时性能指标，存活性，以及在并发标记期间收集的GC效率等数据，外加一些可配置的JVM选项。混合收集的过程，很大程度上和前面的fully-young gc是一样的。

* 注意事项
  * 特别需要注意的是，某些情况下G1触发了Full GC，这时G1会退化使用Serial收集器来完成垃圾的清理工作，它仅 仅使用单线程来完成GC工作，GC暂停时间将达到秒级别的。

  * 并发模式失败

    * G1启动标记周期，但在Mix GC之前，老年代就被填满，这时候G1会放弃标记周期。

    * 解决办法：增加堆大小，或者 调整周期（例如增加线程数-XX:ConcGCThreads等）。
    
  * 晋升失败

    * 没有足够的内存供存活对象或晋升对象使用，由此触发了Full GC(to-space exhausted/to-space overflow）。

    * 解决办法：

      * a)增加 -XX:G1ReservePercent 选项的值（并相应增加总的堆大小）增加预留内存量。

        b)通过减少 -XX:InitiatingHeapOccupancyPercent 提前启动标记周期。

        c)也可以通过增加 -XX:ConcGCThreads 选项的值来增加并行标记线程的数目

    * 巨型对象分配失败

      * 当巨型对象找不到合适的空间进行分配时，就会启动Full GC，来释放空间
      * 解决办法：增加内存或者增大-XX:G1HeapRegionSize

  * 各个GC对比

    * ![image-20201021221045202](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201021221045202.png)
    
  * **常用GC组合**

    * Serial+Serial Old实现单线程的低延迟垃圾回收机制
    * ParNew+CMS，实现多线程的低延迟垃圾回收机制；
    * Parallel Scavenge和Parallel Scavenge Old，实现多线程的高吞吐量垃圾回收机制；


  * **GC如何选择**

    *  如果系统考虑吞吐优先，CPU资源都用来最大程度处理业务，用Parallel GC；

    * 如果系统考虑低延迟有限，每次GC时间尽量短，用CMS GC；

    * 如果系统内存堆较大，同时希望整体来看平均GC时间可控，使用G1 GC。
      对于内存大小的考量：

      一般4G以上，算是比较大，用G1的性价比较高。
      一般超过8G，比如16G-64G内存，非常推荐使用G1 GC。

#### **ZGC**

* 配置参数
  * -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xmx16g
* 特点
  * GC 最大停顿时间不超过 10ms
  * 堆内存支持范围广，小至几百 MB 的堆空间，大至4TB 的超大堆内存（JDK13升至16TB）
  * 与 G1 相比，应用吞吐量下降不超过15%
  * 当前只支持 Linux/x64 位平台，JDK15后支持MacOS和Windows系统

#### **ShennandoahGC**

* 配置参数

  * -XX:+UnlockExperimentalVMOptions 
  * -XX:+UseShenandoahGC -Xmx16g

* 描述

  * Shenandoah GC立项比ZGC更早，设计为GC线程与应用线程并发执行的方式，通过实现垃圾回收过程的并发处理，改善停顿时间，使得GC执行线程能够在业务处理线程运行过程中进行堆压缩、标记和整理，从而消除了绝大部分的暂停时间。

    Shenandoah 团队对外宣称Shenandoah GC的暂停时间与堆大小无关，无论是200 MB 还是 200 GB的堆内存，都可以保障具有很低的暂停时间（注意:并不像ZGC那样保证暂停时间在10ms以内）。

### GC总结

* 到目前为止，我们一共了解了Java目前支持的所有GC算法，一共有7类:

  1. 串行GC（Serial GC）: 单线程执行，应用需要暂停；
  2. 并行GC（ParNew、Parallel Scavenge、Parallel Old）: 多线程并行地执行垃圾回收，关注与高吞吐；
  3. CMS（Concurrent Mark-Sweep）: 多线程并发标记和清除，关注与降低延迟；
  4. G1（G First）: 通过划分多个内存区域做增量整理和回收，进一步降低延迟；
  5. ZGC（Z Garbage Collector）: 通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展
  6. Epsilon: 实验性的GC，供性能分析使用
  7. Shenandoah: G1的改进版本，跟ZGC类似

* 可以看出GC算法和实现的演进路线

  1. 串行 -> 并行: 重复利用多核CPU的优势，大幅降低GC暂停时间，提升吞吐量。

  2. 并行 -> 并发: 不只开多个GC线程并行回收，还将GC操作拆分为多个步骤，让很多繁重的任务和应用线程

  3. 一起并发执行，减少了单次GC暂停持续的时间，这能有效降低业务系统的延迟

  4. CMS -> G1: G1可以说是在CMS基础上进行迭代和优化开发出来的，划分为多个小堆块进行增量回收，这样就更进一步地降低了单次GC暂停的时间

  5. G1 -> ZGC: ZGC号称无停顿垃圾收集器，这又是一次极大的改进。ZGC和G1有一些相似的地方，但是底层

     的算法和思想又有了全新的突破

     