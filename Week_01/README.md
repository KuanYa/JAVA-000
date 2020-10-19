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

* -XX：+UseParallelGC
* -XX：+UseParallelOldGC
* -XX：+UseParallelGC -XX:+UseParallelOldGC

* 年轻代和老年代的垃圾回收都会触发 STW 事件。

* 在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。 

* -XX：ParallelGCThreads=N 来指定 GC 线程数， 其默认值为 CPU 核心数。

* 并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量: 

* 在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短；

* 在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源