###  一、搭建主从

1. 我是直接映射到本地的配置文件，所以在本地直接修改`redis.conf`后，重启即可生效，**不知道此方式是否合理**

2. docker 的搭建参考如下地址

   https://www.runoob.com/docker/docker-run-command.html

3. 执行如下命令，创建Redis实例：

   ```java
   docker run -p 6381:6381 --name redis_6381 -v /c/data/redis/slave1/redis.conf:/etc/redis/redis.conf  -v /c/data/redis/slave1/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
   
   // 解释
   -p 6381:6381:把容器内的6381端口映射到宿主机6381端口
   -v /c/data/redis/slave1/redis.conf:/etc/redis/redis.conf：把宿主机配置好的redis.conf放到容器内的这个位置中
   -v /c/data/redis/slave1/data:/data：把redis持久化的数据在宿主机内显示，做数据备份
   redis redis-server /etc/redis/redis.conf：这个是关键配置，让redis不是无配置启动，而是按照这个redis.conf的配置启动
   –appendonly yes：redis启动后数据持久化
   ```

* 进入Redis实例

  ```java
  docker exec -it redis_6381 /bin/bash
  
  // 解释
  其中 redis_6381 上一步设置的Redis实例的名称
  ```

* 连接Redis实例

  ```java
  redis-cli -h 127.0.0.1 -p 6381
  
  // 连接成功
  PS C:\Program Files\Docker\Docker>  docker exec -it redis_6381 /bin/bash
  root@6ecaf376b1db:/data# redis-cli -h 127.0.0.1 -p 6381
  127.0.0.1:6381>
  ```

* 接下来就可以操作Redis实例。。。。。

4. 参照上述方式，搭建出另外两个Redis实例

   ````
   127.0.0.1:6379>
   
   172.17.0.2:6380>
   ````

   配置文件请参考`redis`文件夹，此时在docker中即可成功启动Redis实例

   ![image-20210106001838070](D:\Java-training-camp\JAVA-000\JAVA-000\Week_12\README.assets\image-20210106001838070.png)

5. 验证主从

   * 首先连接到主库

     ```jsp
     # Replication
     # 当前为主库
     role:master
     # 从库的数量
     connected_slaves:1 
     slave0:ip=172.17.0.3,port=6379,state=online,offset=1242,lag=0
     master_replid:eb3dfa626659242346df6246ffc019ccb0f84b3f
     master_replid2:0000000000000000000000000000000000000000
     master_repl_offset:1242
     second_repl_offset:-1
     repl_backlog_active:1
     repl_backlog_size:1048576
     repl_backlog_first_byte_offset:1
     repl_backlog_histlen:1242
     
     # CPU
     used_cpu_sys:0.675792
     used_cpu_user:0.517949
     used_cpu_sys_children:0.000000
     used_cpu_user_children:0.006355
     
     # Modules
     
     # Cluster
     cluster_enabled:0
     
     # Keyspace
     db0:keys=1,expires=0,avg_ttl=0
     172.17.0.2:6380>
     ```

   * 连接到从库

     ```
     # Replication
     # 当前为从库
     role:slave
     master_host:172.17.0.2
     # 主库的端口
     master_port:6380
     # 主库状态
     master_link_status:up
     master_last_io_seconds_ago:3
     master_sync_in_progress:0
     slave_repl_offset:1396
     slave_priority:100
     slave_read_only:1
     connected_slaves:0
     master_replid:eb3dfa626659242346df6246ffc019ccb0f84b3f
     master_replid2:0000000000000000000000000000000000000000
     master_repl_offset:1396
     second_repl_offset:-1
     repl_backlog_active:1
     repl_backlog_size:1048576
     repl_backlog_first_byte_offset:1
     repl_backlog_histlen:1396
     
     # CPU
     used_cpu_sys:0.798046
     used_cpu_user:0.528413
     used_cpu_sys_children:0.000000
     used_cpu_user_children:0.019510
     
     # Modules
     
     # Cluster
     cluster_enabled:0
     
     # Keyspace
     db0:keys=1,expires=0,avg_ttl=0
     127.0.0.1:6379>
     ```

     经过上面两步，已经验证主库与从库都可以正确连接，下面验证主从一致性

   * 主库中设置值，从库会自动同步，验证如下

     ```java
     # 主库设置键值对
     172.17.0.2:6380> set kuankuan 25
     OK
     172.17.0.2:6380>
     
     # 查看从库是否存在
     127.0.0.1:6379> keys *
     1) "kuankuan"
     2) "11"
     127.0.0.1:6379> get kuankuan
     "25"
     127.0.0.1:6379>
     
     # 经过验证，从库可以自动同步主库的值
     ```

6. 遇到的问题

   * 遇到的主要问题是从节点不能连接到主节点的问题，报错如下：

     ![image-20210107232729920](D:\Java-training-camp\JAVA-000\JAVA-000\Week_12\README.assets\image-20210107232729920.png)

   * 解决办法如下

     * 先查询出主库的实例id值

       ```
       docker ps -a
       ```

       ![image-20210106002737821](D:\Java-training-camp\JAVA-000\JAVA-000\Week_12\README.assets\image-20210106002737821.png)

     * 通过实例id查询出主节点的`IPAddress`

       ```
       docker inspect 7d43bad9e429
       ```

       ![image-20210106002935425](D:\Java-training-camp\JAVA-000\JAVA-000\Week_12\README.assets\image-20210106002935425.png)

     * 将查询出的`IPAddress`配置到主节点 `redis.conf `中，位置如下：

       ```java
       # internet, binding to all the interfaces is dangerous and will expose the
       # instance to everybody on the internet. So by default we uncomment the
       # following bind directive, that will force Redis to listen only on the
       # IPv4 loopback interface address (this means Redis will only be able to
       # accept client connections from the same host that it is running on).
       #
       # IF YOU ARE SURE YOU WANT YOUR INSTANCE TO LISTEN TO ALL THE INTERFACES
       # JUST COMMENT OUT THE FOLLOWING LINE.
       # ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        bind 172.17.0.2 ::1
       ```

     * 从节点中也需要配置，位置如下：

       ````java
       # 如果有多个从节点，配置相同
       replicaof 172.17.0.2 6380
       ````
     
   * 总结
   
     * 综上，使用Redis配置主从完成，通过此次主从的搭建，一定程度上锻炼自己的动手能力，并且了解入门了`docker`,后面会继续完成 `哨兵`和`集群`的搭建
   
### 二、哨兵搭建

   ###  三、集群搭建



