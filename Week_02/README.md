#### 第3课作业实践

#### 1. GCLogAnalysis.java演练

* 运行环境

  * win7 
  * 4核8G
  
* 前置条件

  * **四种GC收集器，默认的最佳堆内存大小均为1/4物理内存（2g）**

* **串行 GC**
  * **参数一**：

     *  `java -XX:+UseSerialGC -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

  * **日志情况**

     * `2020-10-26T22:48:10.811+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.811+0800: [DefNew: 34799K->4352K(39296K), 0.0081935 secs] 34799K->10068K(126720K), 0.0089497 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:10.836+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.836+0800: [DefNew: 39235K->4350K(39296K), 0.0127280 secs] 44951K->20009K(126720K), 0.0132602 secs] [Times: user=0.01 sys=0.02, real=0.01 secs]
       2020-10-26T22:48:10.863+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.864+0800: [DefNew: 39030K->4351K(39296K), 0.0067681 secs] 54689K->29111K(126720K), 0.0073973 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
       2020-10-26T22:48:10.884+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.884+0800: [DefNew: 39295K->4348K(39296K), 0.0123804 secs] 64055K->41443K(126720K), 0.0129343 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:10.906+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.906+0800: [DefNew: 39041K->4348K(39296K), 0.0123676 secs] 76136K->54544K(126720K), 0.0128375 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
       2020-10-26T22:48:10.929+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.929+0800: [DefNew: 39292K->4343K(39296K), 0.0080094 secs] 89488K->66368K(126720K), 0.0086359 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
       2020-10-26T22:48:10.949+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.950+0800: [DefNew: 39264K->4351K(39296K), 0.0123119 secs] 101289K->81175K(126720K), 0.0127283 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:10.971+0800: [GC (Allocation Failure) 2020-10-26T22:48:10.971+0800: [DefNew: 38866K->38866K(39296K), 0.0003075 secs]2020-10-26T22:48:10.972+0800: [Tenured: 76823K->87328K(87424K), 0.0256799 secs] 115690K->89245K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0270608 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
       2020-10-26T22:48:11.011+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.012+0800: [Tenured: 87328K->86738K(87424K), 0.0245744 secs] 126503K->97397K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0254038 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
       2020-10-26T22:48:11.047+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.048+0800: [Tenured: 86738K->87120K(87424K), 0.0226928 secs] 125364K->105776K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0234104 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
       2020-10-26T22:48:11.080+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.081+0800: [Tenured: 87401K->87415K(87424K), 0.0345613 secs] 126667K->105772K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0350452 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
       2020-10-26T22:48:11.121+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.121+0800: [Tenured: 87415K->87415K(87424K), 0.0101603 secs] 126708K->114092K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0106214 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.135+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.135+0800: [Tenured: 87415K->87415K(87424K), 0.0031106 secs] 126532K->116750K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0036386 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
       2020-10-26T22:48:11.144+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.144+0800: [Tenured: 87415K->87415K(87424K), 0.0083106 secs] 126316K->118521K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0089217 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.155+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.155+0800: [Tenured: 87415K->86865K(87424K), 0.0399052 secs] 126593K->117154K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0404747 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
       2020-10-26T22:48:11.199+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.199+0800: [Tenured: 87247K->87247K(87424K), 0.0066775 secs] 126531K->119341K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0072848 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.212+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.212+0800: [Tenured: 87391K->87391K(87424K), 0.0029427 secs] 126651K->122122K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0035824 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
       2020-10-26T22:48:11.217+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.217+0800: [Tenured: 87391K->87391K(87424K), 0.0094354 secs] 126677K->122774K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0100303 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.230+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.230+0800: [Tenured: 87391K->87367K(87424K), 0.0266173 secs] 126594K->121845K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0274089 secs] [Times: user=0.01 sys=0.00, real=0.03 secs]
       2020-10-26T22:48:11.259+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.259+0800: [Tenured: 87367K->87367K(87424K), 0.0074834 secs] 125991K->123840K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0078670 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.267+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.268+0800: [Tenured: 87367K->87367K(87424K), 0.0038715 secs] 126521K->125387K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0042917 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.273+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.274+0800: [Tenured: 87402K->87402K(87424K), 0.0064600 secs] 126688K->125522K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0069487 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.281+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.281+0800: [Tenured: 87402K->87095K(87424K), 0.0370350 secs] 126663K->125607K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0376797 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
       2020-10-26T22:48:11.319+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.319+0800: [Tenured: 87095K->87095K(87424K), 0.0019374 secs] 126347K->125535K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0022873 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
       2020-10-26T22:48:11.322+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.322+0800: [Tenured: 87095K->87095K(87424K), 0.0059258 secs] 126133K->125652K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0064759 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
       2020-10-26T22:48:11.330+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.330+0800: [Tenured: 87095K->87095K(87424K), 0.0029521 secs] 126080K->126080K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0034100 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
       2020-10-26T22:48:11.333+0800: [Full GC (Allocation Failure) 2020-10-26T22:48:11.334+0800: [Tenured: 87095K->87329K(87424K), 0.0343003 secs] 126080K->125889K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0347476 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
       Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
               at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:48)
               at GCLogAnalysis.main(GCLogAnalysis.java:25)`

  *  **小结**：

     *  **堆内存很小时，程序会先发生Yong GC，然后发生Full GC ,直到OOM内存溢出。**

  *  **参数二**：

     *  `java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

  *  **日志情况**：

     *  `2020-10-26T22:59:28.308+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.308+0800: [DefNew: 139776K->17471K(157248K), 0.0324356 secs] 139776K->44997K(506816K), 0.0332499 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
        2020-10-26T22:59:28.383+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.383+0800: [DefNew: 157227K->17471K(157248K), 0.0404091 secs] 184753K->89781K(506816K), 0.0411117 secs] [Times: user=0.03 sys=0.01, real=0.04 secs]
        2020-10-26T22:59:28.463+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.464+0800: [DefNew: 157247K->17472K(157248K), 0.0350178 secs] 229557K->135485K(506816K), 0.0356617 secs] [Times: user=0.02 sys=0.02, real=0.04 secs]
        2020-10-26T22:59:28.544+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.545+0800: [DefNew: 157248K->17471K(157248K), 0.0425117 secs] 275261K->183698K(506816K), 0.0437206 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
        2020-10-26T22:59:28.628+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.628+0800: [DefNew: 157247K->17471K(157248K), 0.0380909 secs] 323474K->227987K(506816K), 0.0390591 secs] [Times: user=0.02 sys=0.02, real=0.04 secs]
        2020-10-26T22:59:28.709+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.710+0800: [DefNew: 156976K->17471K(157248K), 0.0343953 secs] 367491K->268971K(506816K), 0.0351250 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
        2020-10-26T22:59:28.788+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.789+0800: [DefNew: 157247K->17470K(157248K), 0.0316926 secs] 408747K->306924K(506816K), 0.0322359 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
        2020-10-26T22:59:28.858+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.864+0800: [DefNew: 156773K->17471K(157248K), 0.0422608 secs] 446228K->357373K(506816K), 0.0478790 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
        2020-10-26T22:59:28.940+0800: [GC (Allocation Failure) 2020-10-26T22:59:28.941+0800: [DefNew: 157247K->157247K(157248K), 0.0001619 secs]2020-10-26T22:59:28.941+0800: [Tenured: 339901K->286785K(349568K), 0.0734348 secs] 497149K->286785K(506816K), [Metaspace: 2688K->2688K(1056768K)], 0.0741486 secs] [Times: user=0.08 sys=0.00, real=0.08 secs]
        2020-10-26T22:59:29.046+0800: [GC (Allocation Failure) 2020-10-26T22:59:29.046+0800: [DefNew: 139776K->17471K(157248K), 0.0175750 secs] 426561K->331330K(506816K), 0.0179301 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
        2020-10-26T22:59:29.105+0800: [GC (Allocation Failure) 2020-10-26T22:59:29.105+0800: [DefNew: 157247K->157247K(157248K), 0.0001428 secs]2020-10-26T22:59:29.105+0800: [Tenured: 313859K->311201K(349568K), 0.0843765 secs] 471106K->311201K(506816K), [Metaspace: 2688K->2688K(1056768K)], 0.0850405 secs] [Times: user=0.08 sys=0.00, real=0.08 secs]`

        `执行结束!共生成对象次数:5790`

     *  **小结**：
        
        *  随着对内存的增大，系统GC次数明显减少，并且系统正常运行，单位时间内系统的吞吐量明显提高
     *  **参数三**：
        
        *  `java -XX:+UseSerialGC -Xms1024m -Xmx1024m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
     *  **日志情况**：
        *  `2020-10-26T23:01:39.057+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.057+0800: [DefNew: 279616K->34944K(314560K), 0.0551184 secs] 279616K->87003K(1013632K), 0.0558296 secs] [Times: user=0.00 sys=0.05, real=0.06 secs]
           2020-10-26T23:01:39.185+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.186+0800: [DefNew: 314252K->34943K(314560K), 0.0715462 secs] 366312K->167151K(1013632K), 0.0722548 secs] [Times: user=0.02 sys=0.05, real=0.07 secs]
           2020-10-26T23:01:39.327+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.327+0800: [DefNew: 314559K->34941K(314560K), 0.0590861 secs] 446767K->244545K(1013632K), 0.0597954 secs] [Times: user=0.03 sys=0.03, real=0.06 secs]
           2020-10-26T23:01:39.452+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.453+0800: [DefNew: 314557K->34942K(314560K), 0.0674780 secs] 524161K->328342K(1013632K), 0.0681305 secs] [Times: user=0.02 sys=0.05, real=0.07 secs]
           2020-10-26T23:01:39.579+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.581+0800: [DefNew: 314508K->34943K(314560K), 0.0617472 secs] 607908K->413725K(1013632K), 0.0624054 secs] [Times: user=0.03 sys=0.03, real=0.06 secs]
           2020-10-26T23:01:39.703+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.704+0800: [DefNew: 314559K->34943K(314560K), 0.0574362 secs] 693341K->491439K(1013632K), 0.0579889 secs] [Times: user=0.03 sys=0.03, real=0.06 secs]
           2020-10-26T23:01:39.827+0800: [GC (Allocation Failure) 2020-10-26T23:01:39.828+0800: [DefNew: 314559K->34942K(314560K), 0.0556850 secs] 771055K->566653K(1013632K), 0.0573841 secs] [Times: user=0.02 sys=0.03, real=0.06 secs]
           执行结束!共生成对象次数:7350`
     *  **小结**：
        
        * **当对内存不断增大时，系统发生GC的次数不断减少，并且单位时间内创建的对象数也有所提高，所以，提高堆内存的大小有有助于提高系统的吞吐量。**
     *  **参数四**：
        
        *  `java -XX:+UseSerialGC -Xms2048m -Xmx2048m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
     *  **日志情况**
        *  `2020-10-26T23:10:10.489+0800: [GC (Allocation Failure) 2020-10-26T23:10:10.490+0800: [DefNew: 559232K->69888K(629120K), 0.0990651 secs] 559232K->171615K(2027264K), 0.0996784 secs] [Times: user=0.05 sys=0.06, real=0.10 secs]
           2020-10-26T23:10:10.712+0800: [GC (Allocation Failure) 2020-10-26T23:10:10.712+0800: [DefNew: 629120K->69887K(629120K), 0.1074563 secs] 730847K->290854K(2027264K), 0.1080366 secs] [Times: user=0.06 sys=0.05, real=0.11 secs]
           2020-10-26T23:10:10.933+0800: [GC (Allocation Failure) 2020-10-26T23:10:10.934+0800: [DefNew: 629119K->69887K(629120K), 0.0893993 secs] 850086K->414154K(2027264K), 0.0901783 secs] [Times: user=0.05 sys=0.03, real=0.09 secs]
         2020-10-26T23:10:11.132+0800: [GC (Allocation Failure) 2020-10-26T23:10:11.133+0800: [DefNew: 629119K->69888K(629120K), 0.1168016 secs] 973386K->547387K(2027264K), 0.1173499 secs] [Times: user=0.05 sys=0.08, real=0.12 secs]
           执行结束!共生成对象次数:8622`
     *  **小结**：
        
        *  **当堆内存调整到物理内存的1/4是， 程序单位时间内生成的对象数增加，GC 次数减少，但是，系统每次的GC暂停时间增加，这是因为，串行GC不能发挥多核CPU优势导致并且堆内存变大，需要清理的空间变大，GC时间也会变大。**
  
* **并行 GC**

  * **参数一**
    * `java -XX:+UseParallelGC -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:16:40.544+0800: [GC (Allocation Failure) [PSYoungGen: 33280K->5114K(38400K)] 33280K->12713K(125952K), 0.0117843 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.574+0800: [GC (Allocation Failure) [PSYoungGen: 38145K->5117K(38400K)] 45744K->22833K(125952K), 0.0051439 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.596+0800: [GC (Allocation Failure) [PSYoungGen: 37803K->5108K(38400K)] 55520K->34686K(125952K), 0.0114961 secs] [Times: user=0.03 sys=0.03, real=0.01 secs]
      2020-10-26T23:16:40.616+0800: [GC (Allocation Failure) [PSYoungGen: 38215K->5100K(38400K)] 67793K->48467K(125952K), 0.0105522 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.639+0800: [GC (Allocation Failure) [PSYoungGen: 38380K->5115K(38400K)] 81747K->58434K(125952K), 0.0046918 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.656+0800: [GC (Allocation Failure) [PSYoungGen: 38250K->5101K(19968K)] 91569K->68668K(107520K), 0.0044378 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.665+0800: [GC (Allocation Failure) [PSYoungGen: 19829K->7888K(29184K)] 83396K->73889K(116736K), 0.0083795 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
      2020-10-26T23:16:40.677+0800: [GC (Allocation Failure) [PSYoungGen: 22736K->9041K(29184K)] 88737K->76575K(116736K), 0.0032025 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:16:40.690+0800: [GC (Allocation Failure) [PSYoungGen: 23368K->12739K(29184K)] 90901K->81414K(116736K), 0.0038648 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:16:40.698+0800: [GC (Allocation Failure) [PSYoungGen: 27276K->10396K(29184K)] 95951K->86675K(116736K), 0.0101716 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.708+0800: [Full GC (Ergonomics) [PSYoungGen: 10396K->0K(29184K)] [ParOldGen: 76278K->81894K(87552K)] 86675K->81894K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0329334 secs] [Times: user=0.13 sys=0.00, real=0.03 secs]
      2020-10-26T23:16:40.745+0800: [Full GC (Ergonomics) [PSYoungGen: 14824K->0K(29184K)] [ParOldGen: 81894K->85597K(87552K)] 96719K->85597K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0321795 secs] [Times: user=0.09 sys=0.00, real=0.03 secs]
      2020-10-26T23:16:40.784+0800: [Full GC (Ergonomics) [PSYoungGen: 14721K->1195K(29184K)] [ParOldGen: 85597K->87461K(87552K)] 100319K->88656K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0296119 secs] [Times: user=0.08 sys=0.01, real=0.03 secs]
      2020-10-26T23:16:40.822+0800: [Full GC (Ergonomics) [PSYoungGen: 14848K->3758K(29184K)] [ParOldGen: 87461K->87518K(87552K)] 102309K->91276K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0302122 secs] [Times: user=0.08 sys=0.03, real=0.03 secs]
      2020-10-26T23:16:40.857+0800: [Full GC (Ergonomics) [PSYoungGen: 14691K->4883K(29184K)] [ParOldGen: 87518K->87082K(87552K)] 102210K->91966K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0301721 secs] [Times: user=0.05 sys=0.00, real=0.03 secs]
      2020-10-26T23:16:40.890+0800: [Full GC (Ergonomics) [PSYoungGen: 14634K->8254K(29184K)] [ParOldGen: 87082K->87487K(87552K)] 101717K->95742K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0219253 secs] [Times: user=0.05 sys=0.00, real=0.02 secs]
      2020-10-26T23:16:40.914+0800: [Full GC (Ergonomics) [PSYoungGen: 14848K->10924K(29184K)] [ParOldGen: 87487K->86985K(87552K)] 102335K->97910K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0332636 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
      2020-10-26T23:16:40.950+0800: [Full GC (Ergonomics) [PSYoungGen: 14570K->11411K(29184K)] [ParOldGen: 86985K->86985K(87552K)] 101555K->98396K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0072218 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:40.959+0800: [Full GC (Ergonomics) [PSYoungGen: 14848K->13264K(29184K)] [ParOldGen: 86985K->86919K(87552K)] 101833K->100184K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0375696 secs] [Times: user=0.14 sys=0.00, real=0.04 secs]
      2020-10-26T23:16:40.997+0800: [Full GC (Ergonomics) [PSYoungGen: 14776K->13926K(29184K)] [ParOldGen: 86919K->86864K(87552K)] 101696K->100790K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0090044 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:41.007+0800: [Full GC (Ergonomics) [PSYoungGen: 14451K->13926K(29184K)] [ParOldGen: 86864K->86864K(87552K)] 101315K->100790K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0025724 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:16:41.009+0800: [Full GC (Ergonomics) [PSYoungGen: 14664K->14601K(29184K)] [ParOldGen: 86864K->86864K(87552K)] 101528K->101466K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0024788 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
      2020-10-26T23:16:41.012+0800: [Full GC (Ergonomics) [PSYoungGen: 14601K->14601K(29184K)] [ParOldGen: 87365K->86864K(87552K)] 101967K->101466K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0071564 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:16:41.020+0800: [Full GC (Ergonomics) [PSYoungGen: 14601K->14601K(29184K)] [ParOldGen: 87525K->86864K(87552K)] 102127K->101466K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0037406 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:16:41.024+0800: [Full GC (Allocation Failure) [PSYoungGen: 14601K->14601K(29184K)] [ParOldGen: 86864K->86845K(87552K)] 101466K->101447K(116736K), [Metaspace: 2688K->2688K(1056768K)], 0.0279644 secs] [Times: user=0.08 sys=0.00, real=0.03 secs]
      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
              at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:48)
              at GCLogAnalysis.main(GCLogAnalysis.java:25)`
  * **小结**
    * **系统发生频繁的Young GC和Full GC，最后当每次Full GC不能回收掉垃圾，释放空间时，程序OOM，因此，堆内存过小会导致GC次数频繁甚至OOM**
  * **参数二**
    * `java -XX:+UseParallelGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:20:20.839+0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21500K(153088K)] 131584K->43822K(502784K), 0.0178322 secs] [Times: user=0.03 sys=0.03, real=0.02 secs]
      2020-10-26T23:20:20.896+0800: [GC (Allocation Failure) [PSYoungGen: 153084K->21498K(153088K)] 175406K->82663K(502784K), 0.0251696 secs] [Times: user=0.05 sys=0.05, real=0.03 secs]
      2020-10-26T23:20:20.963+0800: [GC (Allocation Failure) [PSYoungGen: 152539K->21493K(153088K)] 213703K->121547K(502784K), 0.0247331 secs] [Times: user=0.00 sys=0.06, real=0.02 secs]
      2020-10-26T23:20:21.021+0800: [GC (Allocation Failure) [PSYoungGen: 153077K->21501K(153088K)] 253131K->164243K(502784K), 0.0210150 secs] [Times: user=0.08 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.078+0800: [GC (Allocation Failure) [PSYoungGen: 153085K->21495K(153088K)] 295827K->206242K(502784K), 0.0245339 secs] [Times: user=0.02 sys=0.03, real=0.02 secs]
      2020-10-26T23:20:21.139+0800: [GC (Allocation Failure) [PSYoungGen: 153079K->21495K(80384K)] 337826K->247199K(430080K), 0.0212784 secs] [Times: user=0.03 sys=0.03, real=0.02 secs]
      2020-10-26T23:20:21.179+0800: [GC (Allocation Failure) [PSYoungGen: 80375K->32285K(116736K)] 306079K->265399K(466432K), 0.0163608 secs] [Times: user=0.01 sys=0.03, real=0.02 secs]
      2020-10-26T23:20:21.212+0800: [GC (Allocation Failure) [PSYoungGen: 91165K->47270K(116736K)] 324279K->285239K(466432K), 0.0210690 secs] [Times: user=0.13 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.246+0800: [GC (Allocation Failure) [PSYoungGen: 106048K->57849K(116736K)] 344017K->303793K(466432K), 0.0249085 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.286+0800: [GC (Allocation Failure) [PSYoungGen: 116672K->41480K(116736K)] 362616K->323208K(466432K), 0.0250327 secs] [Times: user=0.08 sys=0.05, real=0.03 secs]
      2020-10-26T23:20:21.327+0800: [GC (Allocation Failure) [PSYoungGen: 100360K->21554K(116736K)] 382088K->343180K(466432K), 0.0271384 secs] [Times: user=0.09 sys=0.03, real=0.03 secs]
      2020-10-26T23:20:21.355+0800: [Full GC (Ergonomics) [PSYoungGen: 21554K->0K(116736K)] [ParOldGen: 321626K->237262K(349696K)] 343180K->237262K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0746936 secs] [Times: user=0.22 sys=0.00, real=0.08 secs]
      2020-10-26T23:20:21.441+0800: [GC (Allocation Failure) [PSYoungGen: 58880K->23009K(116736K)] 296142K->260272K(466432K), 0.0057756 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:20:21.462+0800: [GC (Allocation Failure) [PSYoungGen: 81889K->21402K(116736K)] 319152K->279128K(466432K), 0.0160403 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.495+0800: [GC (Allocation Failure) [PSYoungGen: 80104K->19939K(116736K)] 337830K->298289K(466432K), 0.0161240 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.528+0800: [GC (Allocation Failure) [PSYoungGen: 78819K->17791K(116736K)] 357169K->316040K(466432K), 0.0152099 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.544+0800: [Full GC (Ergonomics) [PSYoungGen: 17791K->0K(116736K)] [ParOldGen: 298249K->266960K(349696K)] 316040K->266960K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0782161 secs] [Times: user=0.30 sys=0.00, real=0.08 secs]
      2020-10-26T23:20:21.637+0800: [GC (Allocation Failure) [PSYoungGen: 58776K->23986K(116736K)] 325736K->290946K(466432K), 0.0065866 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:20:21.661+0800: [GC (Allocation Failure) [PSYoungGen: 82830K->17587K(116736K)] 349791K->308057K(466432K), 0.0160301 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.695+0800: [GC (Allocation Failure) [PSYoungGen: 76467K->17790K(116736K)] 366937K->324803K(466432K), 0.0152988 secs] [Times: user=0.03 sys=0.00, real=0.02 secs]
      2020-10-26T23:20:21.711+0800: [Full GC (Ergonomics) [PSYoungGen: 17790K->0K(116736K)] [ParOldGen: 307013K->282173K(349696K)] 324803K->282173K(466432K), [Metaspace: 2688K->2688K(1056768K)], 0.0856870 secs] [Times: user=0.38 sys=0.00, real=0.09 secs]
      执行结束!共生成对象次数:5600`
  * **小结**
    * **当提高堆内存后，系统Young GC 次数减少，Full GC次数明显减少，程序正常运行，因此增大堆内存可以保持程序正常运行**
  * **参数三**
    * `java -XX:+UseParallelGC -Xms1024m -Xmx1024m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:23:40.202+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43512K(305664K)] 262144K->71380K(1005056K), 0.0283733 secs] [Times: user=0.02 sys=0.06, real=0.03 secs]
      2020-10-26T23:23:40.294+0800: [GC (Allocation Failure) [PSYoungGen: 305656K->43517K(305664K)] 333524K->147204K(1005056K), 0.0398979 secs] [Times: user=0.05 sys=0.08, real=0.04 secs]
      2020-10-26T23:23:40.399+0800: [GC (Allocation Failure) [PSYoungGen: 305661K->43513K(305664K)] 409348K->214791K(1005056K), 0.0380676 secs] [Times: user=0.13 sys=0.06, real=0.04 secs]
      2020-10-26T23:23:40.488+0800: [GC (Allocation Failure) [PSYoungGen: 305657K->43517K(305664K)] 476935K->294084K(1005056K), 0.0419210 secs] [Times: user=0.11 sys=0.05, real=0.04 secs]
      2020-10-26T23:23:40.585+0800: [GC (Allocation Failure) [PSYoungGen: 305661K->43515K(305664K)] 556228K->362750K(1005056K), 0.0352661 secs] [Times: user=0.11 sys=0.02, real=0.04 secs]
      2020-10-26T23:23:40.677+0800: [GC (Allocation Failure) [PSYoungGen: 305659K->43518K(160256K)] 624894K->429399K(859648K), 0.0441375 secs] [Times: user=0.09 sys=0.05, real=0.04 secs]
      2020-10-26T23:23:40.754+0800: [GC (Allocation Failure) [PSYoungGen: 160254K->68742K(232960K)] 546135K->462277K(932352K), 0.0280644 secs] [Times: user=0.09 sys=0.02, real=0.03 secs]
      2020-10-26T23:23:40.807+0800: [GC (Allocation Failure) [PSYoungGen: 185478K->94442K(232960K)] 579013K->496466K(932352K), 0.0374221 secs] [Times: user=0.14 sys=0.00, real=0.04 secs]
      2020-10-26T23:23:40.872+0800: [GC (Allocation Failure) [PSYoungGen: 211178K->103549K(232960K)] 613202K->522769K(932352K), 0.0424557 secs] [Times: user=0.13 sys=0.00, real=0.04 secs]
      2020-10-26T23:23:40.941+0800: [GC (Allocation Failure) [PSYoungGen: 220285K->77857K(232960K)] 639505K->558303K(932352K), 0.0553660 secs] [Times: user=0.13 sys=0.03, real=0.06 secs]
      2020-10-26T23:23:41.022+0800: [GC (Allocation Failure) [PSYoungGen: 194593K->40507K(232960K)] 675039K->591319K(932352K), 0.0499367 secs] [Times: user=0.05 sys=0.06, real=0.05 secs]
      执行结束!共生成对象次数:8148`
  * **小结**
    * **随着进一步增大堆内存,系统GC次数明显减少，并且单位时间内生成的对象数也增加，因此，增大内存可以提高系统吞吐量，但是，每次GC 的时间会略有增加**
  * **参数四**
    * `java -XX:+UseParallelGC -Xms2048m -Xmx2048m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:31:57.590+0800: [GC (Allocation Failure) [PSYoungGen: 524268K->87037K(611840K)] 524268K->138459K(2010112K), 0.0489892 secs] [Times: user=0.13 sys=0.06, real=0.05 secs]
      2020-10-26T23:31:57.756+0800: [GC (Allocation Failure) [PSYoungGen: 611837K->87029K(611840K)] 663259K->237521K(2010112K), 0.0755793 secs] [Times: user=0.13 sys=0.11, real=0.08 secs]
      2020-10-26T23:31:57.949+0800: [GC (Allocation Failure) [PSYoungGen: 611829K->87039K(611840K)] 762321K->351052K(2010112K), 0.0606790 secs] [Times: user=0.11 sys=0.08, real=0.06 secs]
      2020-10-26T23:31:58.114+0800: [GC (Allocation Failure) [PSYoungGen: 611839K->87030K(611840K)] 875852K->461281K(2010112K), 0.0612933 secs] [Times: user=0.14 sys=0.05, real=0.06 secs]
      2020-10-26T23:31:58.284+0800: [GC (Allocation Failure) [PSYoungGen: 611830K->87039K(611840K)] 986081K->577771K(2010112K), 0.0739665 secs] [Times: user=0.19 sys=0.09, real=0.07 secs]
      执行结束!共生成对象次数:9880`
  * **小结**
    * **对内存设置为1/4物理内存后，GC次数进一步减少，系统单位时间内生成的对象数增加，但是系统每次GC时间会略微增加**

* **并行GC总结**：

  * 系统GC的次数与堆内存大小成反比，但是当堆内存变大时，每次GC的时间会略微增加。

* **CMS GC**

  * **参数一**
    * `java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:35:28.820+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:35:28.821+0800: [Full GC (Allocation Failure) 2020-10-26T23:35:28.822+0800: [CMS2020-10-26T23:35:28.823+0800: [CMS-concurrent-mark: 0.002/0.003 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
       (concurrent mode failure): 87408K->87374K(87424K), 0.0415049 secs] 126581K->125733K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0422910 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]
      2020-10-26T23:35:28.864+0800: [Full GC (Allocation Failure) 2020-10-26T23:35:28.865+0800: [CMS: 87374K->87374K(87424K), 0.0033898 secs] 126426K->125835K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0042934 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:35:28.869+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87374K(87424K)] 126518K(126720K), 0.0004834 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
      2020-10-26T23:35:28.870+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:35:28.871+0800: [Full GC (Allocation Failure) 2020-10-26T23:35:28.871+0800: [CMS2020-10-26T23:35:28.873+0800: [CMS-concurrent-mark: 0.002/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
       (concurrent mode failure): 87374K->87374K(87424K), 0.0089282 secs] 126655K->126610K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0092194 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:35:28.880+0800: [Full GC (Allocation Failure) 2020-10-26T23:35:28.881+0800: [CMS: 87374K->87355K(87424K), 0.0315281 secs] 126610K->126591K(126720K), [Metaspace: 2688K->2688K(1056768K)], 0.0318886 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
              at java.util.Arrays.copyOf(Arrays.java:3332)
              at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
              at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
              at java.lang.StringBuilder.append(StringBuilder.java:208)
              at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:56)
              at GCLogAnalysis.main(GCLogAnalysis.java:25)`
  * **小结**
    * **频繁的Young Gc 和Full GC，最终系统OOM，堆内存过小导致系统无可用空间，最终崩溃**
  * **参数二**
    * `java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:37:48.572+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.572+0800: [ParNew: 139776K->17471K(157248K), 0.0139263 secs] 139776K->47514K(506816K), 0.0146553 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:37:48.631+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.632+0800: [ParNew: 157247K->17471K(157248K), 0.0224043 secs] 187290K->89738K(506816K), 0.0231294 secs] [Times: user=0.06 sys=0.05, real=0.02 secs]
      2020-10-26T23:37:48.697+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.698+0800: [ParNew: 157247K->17472K(157248K), 0.0347117 secs] 229514K->135627K(506816K), 0.0351900 secs] [Times: user=0.11 sys=0.02, real=0.04 secs]
      2020-10-26T23:37:48.778+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.778+0800: [ParNew: 157248K->17472K(157248K), 0.0351678 secs] 275403K->182234K(506816K), 0.0354776 secs] [Times: user=0.06 sys=0.00, real=0.03 secs]
      2020-10-26T23:37:48.850+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.850+0800: [ParNew: 157149K->17472K(157248K), 0.0332405 secs] 321912K->228506K(506816K), 0.0335269 secs] [Times: user=0.11 sys=0.02, real=0.03 secs]
      2020-10-26T23:37:48.884+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 211034K(349568K)] 228692K(506816K), 0.0002678 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:48.885+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:37:48.890+0800: [CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.02 sys=0.01, real=0.01 secs]
      2020-10-26T23:37:48.890+0800: [CMS-concurrent-preclean-start]
      2020-10-26T23:37:48.893+0800: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:48.894+0800: [CMS-concurrent-abortable-preclean-start]
      2020-10-26T23:37:48.926+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.927+0800: [ParNew: 157248K->17471K(157248K), 0.0350608 secs] 368282K->273832K(506816K), 0.0361121 secs] [Times: user=0.08 sys=0.02, real=0.04 secs]
      2020-10-26T23:37:48.995+0800: [GC (Allocation Failure) 2020-10-26T23:37:48.995+0800: [ParNew: 157247K->17472K(157248K), 0.0311641 secs] 413608K->314172K(506816K), 0.0314488 secs] [Times: user=0.05 sys=0.01, real=0.03 secs]
      2020-10-26T23:37:49.060+0800: [GC (Allocation Failure) 2020-10-26T23:37:49.060+0800: [ParNew: 157248K->17471K(157248K), 0.0338196 secs] 453948K->357137K(506816K), 0.0346317 secs] [Times: user=0.08 sys=0.03, real=0.03 secs]
      2020-10-26T23:37:49.094+0800: [CMS-concurrent-abortable-preclean: 0.005/0.200 secs] [Times: user=0.31 sys=0.06, real=0.20 secs]
      2020-10-26T23:37:49.094+0800: [GC (CMS Final Remark) [YG occupancy: 21126 K (157248 K)]2020-10-26T23:37:49.095+0800: [Rescan (parallel) , 0.0003651 secs]2020-10-26T23:37:49.095+0800: [weak refs processing, 0.0001174 secs]2020-10-26T23:37:49.095+0800: [class unloading, 0.0002760 secs]2020-10-26T23:37:49.095+0800: [scrub symbol table, 0.0004703 secs]2020-10-26T23:37:49.096+0800: [scrub string table, 0.0001661 secs][1 CMS-remark: 339665K(349568K)] 360792K(506816K), 0.0017150 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.096+0800: [CMS-concurrent-sweep-start]
      2020-10-26T23:37:49.097+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.097+0800: [CMS-concurrent-reset-start]
      2020-10-26T23:37:49.098+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.129+0800: [GC (Allocation Failure) 2020-10-26T23:37:49.130+0800: [ParNew: 157073K->17470K(157248K), 0.0215433 secs] 458957K->363805K(506816K), 0.0221458 secs] [Times: user=0.11 sys=0.02, real=0.02 secs]
      2020-10-26T23:37:49.153+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 346334K(349568K)] 364366K(506816K), 0.0003352 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.153+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:37:49.158+0800: [CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:37:49.159+0800: [CMS-concurrent-preclean-start]
      2020-10-26T23:37:49.161+0800: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.162+0800: [CMS-concurrent-abortable-preclean-start]
      2020-10-26T23:37:49.162+0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.163+0800: [GC (CMS Final Remark) [YG occupancy: 42990 K (157248 K)]2020-10-26T23:37:49.163+0800: [Rescan (parallel) , 0.0004977 secs]2020-10-26T23:37:49.164+0800: [weak refs processing, 0.0001353 secs]2020-10-26T23:37:49.164+0800: [class unloading, 0.0003042 secs]2020-10-26T23:37:49.164+0800: [scrub symbol table, 0.0004467 secs]2020-10-26T23:37:49.165+0800: [scrub string table, 0.0001975 secs][1 CMS-remark: 346334K(349568K)] 389324K(506816K), 0.0021929 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.165+0800: [CMS-concurrent-sweep-start]
      2020-10-26T23:37:49.166+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.167+0800: [CMS-concurrent-reset-start]
      2020-10-26T23:37:49.167+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.196+0800: [GC (Allocation Failure) 2020-10-26T23:37:49.196+0800: [ParNew: 157246K->17471K(157248K), 0.0205042 secs] 408207K->315754K(506816K), 0.0208005 secs] [Times: user=0.13 sys=0.00, real=0.02 secs]
      2020-10-26T23:37:49.217+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 298283K(349568K)] 316453K(506816K), 0.0003045 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.217+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:37:49.221+0800: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.221+0800: [CMS-concurrent-preclean-start]
      2020-10-26T23:37:49.222+0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.222+0800: [CMS-concurrent-abortable-preclean-start]
      2020-10-26T23:37:49.253+0800: [GC (Allocation Failure) 2020-10-26T23:37:49.253+0800: [ParNew (promotion failed): 157247K->157247K(157248K), 0.0250201 secs]2020-10-26T23:37:49.278+0800: [CMS2020-10-26T23:37:49.278+0800: [CMS-concurrent-abortable-preclean: 0.001/0.055 secs] [Times: user=0.08 sys=0.00, real=0.06 secs]
       (concurrent mode failure): 345773K->305656K(349568K), 0.0861086 secs] 455530K->305656K(506816K), [Metaspace: 2688K->2688K(1056768K)], 0.1115506 secs] [Times: user=0.13 sys=0.00, real=0.11 secs]
      2020-10-26T23:37:49.403+0800: [GC (Allocation Failure) 2020-10-26T23:37:49.403+0800: [ParNew: 139776K->139776K(157248K), 0.0002438 secs]2020-10-26T23:37:49.403+0800: [CMS: 305656K->312155K(349568K), 0.0983712 secs] 445432K->312155K(506816K), [Metaspace: 2688K->2688K(1056768K)], 0.0992084 secs] [Times: user=0.09 sys=0.00, real=0.10 secs]
      2020-10-26T23:37:49.503+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 312155K(349568K)] 312824K(506816K), 0.0013034 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:37:49.504+0800: [CMS-concurrent-mark-start]
      执行结束!共生成对象次数:6233`
  * **小结**
    * **增加堆内存大小，系统可正常运行**
  * **参数三**
    * `java -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:42:19.869+0800: [GC (Allocation Failure) 2020-10-26T23:42:19.870+0800: [ParNew: 272352K->34047K(306688K), 0.0273086 secs] 272352K->85912K(1014528K), 0.0278050 secs] [Times: user=0.05 sys=0.03, real=0.03 secs]
      2020-10-26T23:42:19.960+0800: [GC (Allocation Failure) 2020-10-26T23:42:19.960+0800: [ParNew: 306687K->34048K(306688K), 0.0410067 secs] 358552K->168725K(1014528K), 0.0415594 secs] [Times: user=0.16 sys=0.03, real=0.04 secs]
      2020-10-26T23:42:20.061+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.061+0800: [ParNew: 306688K->34046K(306688K), 0.0495001 secs] 441365K->242750K(1014528K), 0.0499913 secs] [Times: user=0.16 sys=0.03, real=0.05 secs]
      2020-10-26T23:42:20.170+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.170+0800: [ParNew: 306686K->34048K(306688K), 0.0540821 secs] 515390K->322836K(1014528K), 0.0547684 secs] [Times: user=0.17 sys=0.02, real=0.05 secs]
      2020-10-26T23:42:20.283+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.283+0800: [ParNew: 306688K->34048K(306688K), 0.0482593 secs] 595476K->391614K(1014528K), 0.0485854 secs] [Times: user=0.14 sys=0.05, real=0.05 secs]
      2020-10-26T23:42:20.331+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 357566K(707840K)] 391902K(1014528K), 0.0002553 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.332+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:42:20.346+0800: [CMS-concurrent-mark: 0.013/0.013 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
      2020-10-26T23:42:20.346+0800: [CMS-concurrent-preclean-start]
      2020-10-26T23:42:20.347+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.347+0800: [CMS-concurrent-abortable-preclean-start]
      2020-10-26T23:42:20.398+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.398+0800: [ParNew2020-10-26T23:42:20.449+0800: :[CMS-concurrent-abortable-preclean: 0.001/0.101 secs] 306688K->34048K(306688K) [Times: user=0.22 sys=0.01, real=0.11 secs]
      , 0.0555020 secs] 664254K->471189K(1014528K), 0.0563641 secs] [Times: user=0.17 sys=0.01, real=0.06 secs]
      2020-10-26T23:42:20.455+0800: [GC (CMS Final Remark) [YG occupancy: 34756 K (306688 K)]2020-10-26T23:42:20.456+0800: [Rescan (parallel) , 0.0008739 secs]2020-10-26T23:42:20.457+0800: [weak refs processing, 0.0002557 secs]2020-10-26T23:42:20.457+0800: [class unloading, 0.0004458 secs]2020-10-26T23:42:20.458+0800: [scrub symbol table, 0.0005907 secs]2020-10-26T23:42:20.458+0800: [scrub string table, 0.0001935 secs][1 CMS-remark: 437141K(707840K)] 471898K(1014528K), 0.0034114 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
      2020-10-26T23:42:20.462+0800: [CMS-concurrent-sweep-start]
      2020-10-26T23:42:20.464+0800: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.465+0800: [CMS-concurrent-reset-start]
      2020-10-26T23:42:20.468+0800: [CMS-concurrent-reset: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.528+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.528+0800: [ParNew: 306688K->34048K(306688K), 0.0369863 secs] 616325K->428168K(1014528K), 0.0373028 secs] [Times: user=0.13 sys=0.00, real=0.04 secs]
      2020-10-26T23:42:20.629+0800: [GC (Allocation Failure) 2020-10-26T23:42:20.629+0800: [ParNew: 306682K->34048K(306688K), 0.0482454 secs] 700802K->505079K(1014528K), 0.0485449 secs] [Times: user=0.14 sys=0.00, real=0.05 secs]
      2020-10-26T23:42:20.678+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 471031K(707840K)] 505592K(1014528K), 0.0005418 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.679+0800: [CMS-concurrent-mark-start]
      2020-10-26T23:42:20.688+0800: [CMS-concurrent-mark: 0.008/0.008 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
      2020-10-26T23:42:20.688+0800: [CMS-concurrent-preclean-start]
      2020-10-26T23:42:20.691+0800: [CMS-concurrent-preclean: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
      2020-10-26T23:42:20.692+0800: [CMS-concurrent-abortable-preclean-start]
      执行结束!共生成对象次数:8504`
  * **小结**
    * **GC次数进一步减少，单位时间内创建的对象次数更多**
  * **参数四**
    * `java -XX:+UseConcMarkSweepGC -Xms2048m -Xmx2048m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:44:45.409+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.410+0800: [ParNew: 272640K->34046K(306688K), 0.0326518 secs] 272640K->87452K(2063104K), 0.0334296 secs] [Times: user=0.05 sys=0.08, real=0.03 secs]
      2020-10-26T23:44:45.517+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.517+0800: [ParNew: 306686K->34048K(306688K), 0.0386699 secs] 360092K->161473K(2063104K), 0.0396816 secs] [Times: user=0.05 sys=0.06, real=0.04 secs]
      2020-10-26T23:44:45.617+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.617+0800: [ParNew: 306688K->34048K(306688K), 0.0516016 secs] 434113K->237923K(2063104K), 0.0520755 secs] [Times: user=0.16 sys=0.05, real=0.05 secs]
      2020-10-26T23:44:45.733+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.736+0800: [ParNew: 306688K->34048K(306688K), 0.0544912 secs] 510563K->316302K(2063104K), 0.0571995 secs] [Times: user=0.16 sys=0.03, real=0.06 secs]
      2020-10-26T23:44:45.850+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.851+0800: [ParNew: 306688K->34048K(306688K), 0.0544755 secs] 588942K->391875K(2063104K), 0.0549550 secs] [Times: user=0.14 sys=0.06, real=0.06 secs]
      2020-10-26T23:44:45.965+0800: [GC (Allocation Failure) 2020-10-26T23:44:45.965+0800: [ParNew: 306654K->34048K(306688K), 0.0479791 secs] 664482K->462859K(2063104K), 0.0482643 secs] [Times: user=0.16 sys=0.03, real=0.05 secs]
      2020-10-26T23:44:46.070+0800: [GC (Allocation Failure) 2020-10-26T23:44:46.070+0800: [ParNew: 306688K->34048K(306688K), 0.0542264 secs] 735499K->544985K(2063104K), 0.0545340 secs] [Times: user=0.22 sys=0.03, real=0.06 secs]
      2020-10-26T23:44:46.182+0800: [GC (Allocation Failure) 2020-10-26T23:44:46.182+0800: [ParNew: 306688K->34048K(306688K), 0.0580183 secs] 817625K->630141K(2063104K), 0.0583772 secs] [Times: user=0.14 sys=0.05, real=0.06 secs]
      执行结束!共生成对象次数:8304`
  * **小结**
    * **继续增加堆内存，程序单位时间内生成的对象次数变化不大甚至减少，证明系统吞吐量降低，但是GC次数减少几乎只发生Young GC**
  * **CMS总结**
    * **GC次数与堆内存大小成反比，内存越大，发生GC次数越小，当内存达到足够大时（物理内存1/4）,程序几乎不会发生CMS GC，以Young GC为主**
  * CMS GC 六个阶段
    * `GC (CMS Initial Mark) 初始标记 STW` 
    * `CMS-concurrent-mark-start 并发标记`
    * `CMS-concurrent-preclean 并发预清理`
    * `GC (CMS Final Remark) 最终标记 STW`
    * `CMS-concurrent-sweep 并发清理`
    * `CMS-concurrent-reset 并发重置`

* **G1 GC**

  * **参数一**
    * `java -XX:+UseG1GC -Xms512m -Xmx512m -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-26T23:59:08.539+0800: [GC pause (G1 Evacuation Pause) (young) 31M->9778K(512M), 0.0060243 secs]
      2020-10-26T23:59:08.557+0800: [GC pause (G1 Evacuation Pause) (young) 36M->19M(512M), 0.0066902 secs]
      2020-10-26T23:59:08.586+0800: [GC pause (G1 Evacuation Pause) (young) 56M->33M(512M), 0.0042280 secs]
      2020-10-26T23:59:08.607+0800: [GC pause (G1 Evacuation Pause) (young) 75M->46M(512M), 0.0067977 secs]
      2020-10-26T23:59:08.665+0800: [GC pause (G1 Evacuation Pause) (young) 152M->79M(512M), 0.0094228 secs]
      2020-10-26T23:59:08.701+0800: [GC pause (G1 Evacuation Pause) (young) 155M->106M(512M), 0.0067372 secs]
      2020-10-26T23:59:08.758+0800: [GC pause (G1 Evacuation Pause) (young) 238M->141M(512M), 0.0122944 secs]
      2020-10-26T23:59:08.807+0800: [GC pause (G1 Evacuation Pause) (young) 271M->183M(512M), 0.0154360 secs]
      2020-10-26T23:59:08.854+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 279M->219M(512M), 0.0142510 secs]
      2020-10-26T23:59:08.869+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:08.869+0800: [GC concurrent-root-region-scan-end, 0.0003782 secs]
      2020-10-26T23:59:08.870+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:08.872+0800: [GC concurrent-mark-end, 0.0028513 secs]
      2020-10-26T23:59:08.873+0800: [GC remark, 0.0011858 secs]
      2020-10-26T23:59:08.874+0800: [GC cleanup 237M->237M(512M), 0.0004152 secs]
      2020-10-26T23:59:08.927+0800: [GC pause (G1 Evacuation Pause) (young) 381M->268M(512M), 0.0168781 secs]
      2020-10-26T23:59:08.948+0800: [GC pause (G1 Evacuation Pause) (mixed) 275M->255M(512M), 0.0065536 secs]
      2020-10-26T23:59:08.955+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 257M->255M(512M), 0.0011275 secs]
      2020-10-26T23:59:08.957+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:08.957+0800: [GC concurrent-root-region-scan-end, 0.0003024 secs]
      2020-10-26T23:59:08.957+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:08.963+0800: [GC concurrent-mark-end, 0.0059574 secs]
      2020-10-26T23:59:08.965+0800: [GC remark, 0.0022991 secs]
      2020-10-26T23:59:08.968+0800: [GC cleanup 275M->275M(512M), 0.0005016 secs]
      2020-10-26T23:59:09.007+0800: [GC pause (G1 Evacuation Pause) (young) 400M->299M(512M), 0.0114389 secs]
      2020-10-26T23:59:09.023+0800: [GC pause (G1 Evacuation Pause) (mixed) 312M->280M(512M), 0.0142805 secs]
      2020-10-26T23:59:09.038+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 280M->280M(512M), 0.0015348 secs]
      2020-10-26T23:59:09.039+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.040+0800: [GC concurrent-root-region-scan-end, 0.0003066 secs]
      2020-10-26T23:59:09.040+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.045+0800: [GC concurrent-mark-end, 0.0052303 secs]
      2020-10-26T23:59:09.047+0800: [GC remark, 0.0024879 secs]
      2020-10-26T23:59:09.050+0800: [GC cleanup 299M->299M(512M), 0.0004737 secs]
      2020-10-26T23:59:09.080+0800: [GC pause (G1 Evacuation Pause) (young) 404M->312M(512M), 0.0089856 secs]
      2020-10-26T23:59:09.092+0800: [GC pause (G1 Evacuation Pause) (mixed) 324M->293M(512M), 0.0137498 secs]
      2020-10-26T23:59:09.107+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 294M->294M(512M), 0.0015244 secs]
      2020-10-26T23:59:09.109+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.109+0800: [GC concurrent-root-region-scan-end, 0.0004282 secs]
      2020-10-26T23:59:09.110+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.118+0800: [GC concurrent-mark-end, 0.0080258 secs]
      2020-10-26T23:59:09.118+0800: [GC remark, 0.0012875 secs]
      2020-10-26T23:59:09.120+0800: [GC cleanup 315M->315M(512M), 0.0004289 secs]
      2020-10-26T23:59:09.148+0800: [GC pause (G1 Evacuation Pause) (young) 409M->326M(512M), 0.0074855 secs]
      2020-10-26T23:59:09.159+0800: [GC pause (G1 Evacuation Pause) (mixed) 342M->307M(512M), 0.0148326 secs]
      2020-10-26T23:59:09.184+0800: [GC pause (G1 Evacuation Pause) (mixed) 336M->314M(512M), 0.0034714 secs]
      2020-10-26T23:59:09.188+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 316M->315M(512M), 0.0014994 secs]
      2020-10-26T23:59:09.190+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.190+0800: [GC concurrent-root-region-scan-end, 0.0003367 secs]
      2020-10-26T23:59:09.190+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.196+0800: [GC concurrent-mark-end, 0.0056503 secs]
      2020-10-26T23:59:09.197+0800: [GC remark, 0.0025038 secs]
      2020-10-26T23:59:09.200+0800: [GC cleanup 340M->340M(512M), 0.0004425 secs]
      2020-10-26T23:59:09.222+0800: [GC pause (G1 Evacuation Pause) (young) 406M->336M(512M), 0.0109856 secs]
      2020-10-26T23:59:09.238+0800: [GC pause (G1 Evacuation Pause) (mixed) 353M->316M(512M), 0.0126322 secs]
      2020-10-26T23:59:09.257+0800: [GC pause (G1 Evacuation Pause) (mixed) 348M->323M(512M), 0.0074003 secs]
      2020-10-26T23:59:09.265+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 323M->323M(512M), 0.0028449 secs]
      2020-10-26T23:59:09.269+0800: [GC concurrent-root-region-scan-end, 0.0002134 secs]
      2020-10-26T23:59:09.269+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.273+0800: [GC concurrent-mark-end, 0.0037318 secs]
      2020-10-26T23:59:09.273+0800: [GC remark, 0.0013585 secs]
      2020-10-26T23:59:09.274+0800: [GC cleanup 347M->345M(512M), 0.0008832 secs]
      2020-10-26T23:59:09.276+0800: [GC concurrent-cleanup-start]
      2020-10-26T23:59:09.276+0800: [GC concurrent-cleanup-end, 0.0010572 secs]
      2020-10-26T23:59:09.300+0800: [GC pause (G1 Evacuation Pause) (young) 407M->343M(512M), 0.0064433 secs]
      2020-10-26T23:59:09.313+0800: [GC pause (G1 Evacuation Pause) (mixed) 360M->328M(512M), 0.0116692 secs]
      2020-10-26T23:59:09.327+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 328M->328M(512M), 0.0040084 secs]
      2020-10-26T23:59:09.332+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.332+0800: [GC concurrent-root-region-scan-end, 0.0002535 secs]
      2020-10-26T23:59:09.332+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.335+0800: [GC concurrent-mark-end, 0.0036614 secs]
      2020-10-26T23:59:09.336+0800: [GC remark, 0.0014364 secs]
      2020-10-26T23:59:09.337+0800: [GC cleanup 351M->351M(512M), 0.0004242 secs]
      2020-10-26T23:59:09.355+0800: [GC pause (G1 Evacuation Pause) (young) 409M->350M(512M), 0.0092100 secs]
      2020-10-26T23:59:09.370+0800: [GC pause (G1 Evacuation Pause) (mixed) 369M->334M(512M), 0.0136649 secs]
      2020-10-26T23:59:09.390+0800: [GC pause (G1 Evacuation Pause) (mixed) 359M->342M(512M), 0.0063676 secs]
      2020-10-26T23:59:09.398+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 343M->342M(512M), 0.0036896 secs]
      2020-10-26T23:59:09.402+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.402+0800: [GC concurrent-root-region-scan-end, 0.0003448 secs]
      2020-10-26T23:59:09.402+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.406+0800: [GC concurrent-mark-end, 0.0035914 secs]
      2020-10-26T23:59:09.406+0800: [GC remark, 0.0014112 secs]
      2020-10-26T23:59:09.408+0800: [GC cleanup 365M->365M(512M), 0.0005400 secs]
      2020-10-26T23:59:09.423+0800: [GC pause (G1 Evacuation Pause) (young) 410M->361M(512M), 0.0096054 secs]
      2020-10-26T23:59:09.438+0800: [GC pause (G1 Evacuation Pause) (mixed) 382M->346M(512M), 0.0139587 secs]
      2020-10-26T23:59:09.457+0800: [GC pause (G1 Evacuation Pause) (mixed) 368M->346M(512M), 0.0084923 secs]
      2020-10-26T23:59:09.468+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 347M->347M(512M), 0.0029273 secs]
      2020-10-26T23:59:09.471+0800: [GC concurrent-root-region-scan-start]
      2020-10-26T23:59:09.471+0800: [GC concurrent-root-region-scan-end, 0.0002436 secs]
      2020-10-26T23:59:09.471+0800: [GC concurrent-mark-start]
      2020-10-26T23:59:09.476+0800: [GC concurrent-mark-end, 0.0042000 secs]
      2020-10-26T23:59:09.477+0800: [GC remark, 0.0029348 secs]
      2020-10-26T23:59:09.483+0800: [GC cleanup 372M->372M(512M), 0.0008961 secs]
      2020-10-26T23:59:09.494+0800: [GC pause (G1 Evacuation Pause) (young) 409M->368M(512M), 0.0092671 secs]
      执行结束!共生成对象次数:6762`
  * **小结**
    * **当堆内存较小时,系统发生频繁的GC**
  * **参数二**
    * `java -XX:+UseG1GC -Xms1024m -Xmx1024m -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-27T00:00:11.063+0800: [GC pause (G1 Evacuation Pause) (young) 65M->17M(1024M), 0.0062179 secs]
      2020-10-27T00:00:11.090+0800: [GC pause (G1 Evacuation Pause) (young) 75M->41M(1024M), 0.0063792 secs]
      2020-10-27T00:00:11.124+0800: [GC pause (G1 Evacuation Pause) (young) 97M->54M(1024M), 0.0053533 secs]
      2020-10-27T00:00:11.146+0800: [GC pause (G1 Evacuation Pause) (young) 106M->73M(1024M), 0.0087093 secs]
      2020-10-27T00:00:11.189+0800: [GC pause (G1 Evacuation Pause) (young) 150M->100M(1024M), 0.0083593 secs]
      2020-10-27T00:00:11.243+0800: [GC pause (G1 Evacuation Pause) (young) 220M->141M(1024M), 0.0144187 secs]
      2020-10-27T00:00:11.296+0800: [GC pause (G1 Evacuation Pause) (young) 254M->172M(1024M), 0.0132710 secs]
      2020-10-27T00:00:11.362+0800: [GC pause (G1 Evacuation Pause) (young) 320M->219M(1024M), 0.0133004 secs]
      2020-10-27T00:00:11.420+0800: [GC pause (G1 Evacuation Pause) (young) 368M->262M(1024M), 0.0184250 secs]
      2020-10-27T00:00:11.492+0800: [GC pause (G1 Evacuation Pause) (young) 438M->309M(1024M), 0.0190179 secs]
      2020-10-27T00:00:11.709+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 773M->411M(1024M), 0.0417523 secs]
      2020-10-27T00:00:11.752+0800: [GC concurrent-root-region-scan-start]
      2020-10-27T00:00:11.754+0800: [GC concurrent-root-region-scan-end, 0.0015826 secs]
      2020-10-27T00:00:11.754+0800: [GC concurrent-mark-start]
      2020-10-27T00:00:11.759+0800: [GC concurrent-mark-end, 0.0047918 secs]
      2020-10-27T00:00:11.759+0800: [GC remark, 0.0016275 secs]
      2020-10-27T00:00:11.761+0800: [GC cleanup 437M->428M(1024M), 0.0009781 secs]
      2020-10-27T00:00:11.763+0800: [GC concurrent-cleanup-start]
      2020-10-27T00:00:11.763+0800: [GC concurrent-cleanup-end, 0.0001649 secs]
      2020-10-27T00:00:11.815+0800: [GC pause (G1 Evacuation Pause) (young) 605M->439M(1024M), 0.0317515 secs]
      2020-10-27T00:00:11.858+0800: [GC pause (G1 Evacuation Pause) (mixed) 468M->378M(1024M), 0.0157242 secs]
      2020-10-27T00:00:11.914+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 524M->416M(1024M), 0.0122447 secs]
      2020-10-27T00:00:11.927+0800: [GC concurrent-root-region-scan-start]
      2020-10-27T00:00:11.927+0800: [GC concurrent-root-region-scan-end, 0.0003014 secs]
      2020-10-27T00:00:11.927+0800: [GC concurrent-mark-start]
      2020-10-27T00:00:11.932+0800: [GC concurrent-mark-end, 0.0048919 secs]
      2020-10-27T00:00:11.933+0800: [GC remark, 0.0021800 secs]
      2020-10-27T00:00:11.937+0800: [GC cleanup 438M->437M(1024M), 0.0013719 secs]
      2020-10-27T00:00:11.938+0800: [GC concurrent-cleanup-start]
      2020-10-27T00:00:11.938+0800: [GC concurrent-cleanup-end, 0.0001781 secs]
      执行结束!共生成对象次数:7936`
  * **小结**
    * **再次增加系统堆内存，GC次数进一步减少，单位时间内生成的对象数增加，说明系统的吞吐量增加**
  * **参数三**
    * `java -XX:+UseG1GC -Xms2048m -Xmx2048m -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis`
  * **日志情况**
    * `2020-10-27T00:04:59.907+0800: [GC pause (G1 Evacuation Pause) (young) 133M->52M(2048M), 0.0115176 secs]
      2020-10-27T00:04:59.965+0800: [GC pause (G1 Evacuation Pause) (young) 167M->85M(2048M), 0.0128330 secs]
      2020-10-27T00:05:00.018+0800: [GC pause (G1 Evacuation Pause) (young) 192M->120M(2048M), 0.0145252 secs]
      2020-10-27T00:05:00.075+0800: [GC pause (G1 Evacuation Pause) (young) 239M->160M(2048M), 0.0159518 secs]
      2020-10-27T00:05:00.126+0800: [GC pause (G1 Evacuation Pause) (young) 272M->193M(2048M), 0.0140817 secs]
      2020-10-27T00:05:00.176+0800: [GC pause (G1 Evacuation Pause) (young) 307M->229M(2048M), 0.0147089 secs]
      2020-10-27T00:05:00.222+0800: [GC pause (G1 Evacuation Pause) (young) 332M->260M(2048M), 0.0099869 secs]
      2020-10-27T00:05:00.278+0800: [GC pause (G1 Evacuation Pause) (young) 401M->303M(2048M), 0.0167054 secs]
      执行结束!共生成对象次数:7453`
  * **小结**
    * GC次数进一步减小，每次暂停时间基本相同，但是同CMS GC相比，单位时间内生成的对象数减少，单次GC暂停时间变小，因此，相同堆内存下，CMS GC 吞吐量比G1 GC高

##### 1.1 GC 总结
  * 内存小，效率低，GC 发生次数频繁

  * 以上四种GC的发生频率与堆内存大小相关，当堆内存达到合理值时（例如物理内存的1/4）系统发生GC 的频率降低

  * 并行GC CMS ，堆内存变大时，单次暂停GC时间会变大

  * 无论选择哪种GC，配置合理的堆内存大小是关键

  * 

##### 1.2 烦请助教老师指教一下，总结的不足之处
* 
  
####　２.使用压测工具（wrk或sb）

  * `sb -u http://localhost:8088/api/hello -c 20 -N 60`
  * 参数
     	1. `java -jar -XX:+UseParallelGC -Xmx2g -Xms2g gateway-server-0.0.1-SNAPSHOT.jar`
         * 第一次压测吞吐量：2710
         * 第二次压测吞吐量：3158.9
         * 第三次压测吞吐量：3188.9
         * 平均吞吐量：3,019.2
        	2. `java -jar -XX:+UseConcMarkSweepGC -Xmx2g -Xms2g gateway-server-0.0.1-SNAPSHOT.jar`
         * 第一次压测吞吐量：2847.8
         * 第二次压测吞吐量：2868.5
         * 第三次压测吞吐量：3409.3
         * 平均吞吐量：3,041.8
        	3. `java -jar -XX:+UseG1GC -Xmx2g -Xms2g gateway-server-0.0.1-SNAPSHOT.jar`
         * 第一次压测吞吐量：2839.8
         * 第二次压测吞吐量：2574.3
         * 第三次压测吞吐量：2789.1
         * 平均吞吐量：2,734.4

* 总结：
  * 吞吐量： CMS  GC > ParallelGC > G1 GC