学习笔记

### 一、docker 中搭建Redis实例简单操作命令

* 我是直接映射到本地的配置文件

* 执行如下命令，创建Redis实例：

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

* 接下来就可以操作Redis实例



作业一：（**必做）**基于 Redis 封装分布式数据操作：

- 在 Java 中实现一个简单的分布式锁；
- 在 Java 中实现一个分布式计数器，模拟减库存。

#### 二、分布式锁

#### 1.基于单个 Redis 节点实现分布式锁

* **实现思路**

  * Redis 可以使用键值对来保存锁变量，再接收和处理不同客户端发送的加锁和释放锁的操作请求

  * 因为 Redis 使用单线程处理请求，即使多个客户端同时请求，Redis 也会串行处理它们的请求

  * 基于Redis单命令操作和Lua脚本

  * `参考极客时间 Redis核心技术与实战 第30讲 如何实现Redis分布式锁`

  * `参考极客时间 Java性能调优实战 第41讲 如何设计更优的分布式锁`

    

* **代码实现流程如下（未完善）**

  * **首先定义获取锁的代码**

    ````java
    public class GetLock {
    
        // 是否加锁成功
        private static final String LOCK_SUCCESS = "OK";
        // NX 选项，SET 命令只有在键值对不存在时，才会进行设置，否则不做赋值操作
        private static final String SET_IF_NOT_EXIST = "NX";
        // SET 命令在执行时还可以带上 EX 或 PX 选项，用来设置键值对的过期时间。
        private static final String SET_WITH_EXPIRE_TIME = "PX";
    
        // lock_key
        public static final String LOCK_KEY = "DISTRIBUTED_LOCK";
    
        // 解锁脚本
        public static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    
        /**
         * 尝试获取分布式锁 *
         *
         * @param jedis      Redis客户端 *
         * @param lockKey    锁 *
         * @param requestId  请求标识 *
         * @param expireTime 超期时间 *
         * @return 是否获取成功
         */
        public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
            // 只有 key 不存在时，SET 才会创建 key，并对 key 进行赋值
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        }
    }
    ````

  * **定义客户端**

    ````java
    public class Client {
    
        // 每一个客户端生成一个随机数，作为value值，释放锁是根据唯一值判断，避免错误释放锁
        private static final String RANDOM_UUID = UUID.randomUUID().toString();
    
        public static void main(String[] args) {
            // 模拟一个客户端获取锁
            Jedis jedis = new Jedis("localhost", 6379);
            boolean flag = GetLock.tryGetDistributedLock(jedis, GetLock.LOCK_KEY, RANDOM_UUID, 100000);
            try {
                if (flag) {
                    // 代表获取到锁，可以执行后面你的操作
                    System.out.println("我获取到了锁 。。。。。。。。。。");
                } else {
                    System.out.println("获取锁失败，锁已经被其他客户端持有。。。。");
                }
            } finally {
                // 释放锁
                // jedis.eval(GetLock.SCRIPT_UNLOCK, 1, GetLock.LOCK_KEY, RANDOM_UUID);
            }
        }
    }
    ````

  * **执行客户端**

    * 执行客户端前，我们可以看到redis服务器中不存在 `键值对` 值

      ![image-20210103233024354](D:\Java-training-camp\JAVA-000\JAVA-000\Week_11\README.assets\image-20210103233024354.png)

    * **客户端一加锁**

      * 执行客户端一后，可以看到，redis中已经创建`键值对`,并且已经获取到了锁

        * **获取锁成功**

          ````java
          我获取到了锁 。。。。。。。。。。客户一唯一值 8e7f1a18-1239-4e53-87c1-26d85220e0aa
          ````

          

        * **查看是否已经成功创建`键值对`**

          ````java
          127.0.0.1:6379> get DISTRIBUTED_LOCK
          "56763685-2b4c-4fee-89dd-fdab81769c66"
          127.0.0.1:6379>
          ````

          通过查看redis中的value值，可以看到客户端一已经成功加锁（**超时时间内，会一直持有锁**）

    * 客户端二获取锁

      * 执行`客户端二`

        ````java
        获取锁失败，锁已经被其他客户端持有。。。。
        ````

        由于客户端一已经设置了键值对的值，所以客户端二不能创建键值对，所以，客户端二不能加锁成功，只有客户端一删除键值对后，客户端二才可加锁

    * 客户端一释放锁

      ````java
      // 使用Lua 脚本执行释放锁的操作，伪代码如下
      // 解锁脚本
      public static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
      
      // 执行解锁脚本
      jedis.eval(GetLock.SCRIPT_UNLOCK, 1, GetLock.LOCK_KEY, RANDOM_UUID);
      ````

      ````java
      127.0.0.1:6379> get DISTRIBUTED_LOCK
      (nil)
      127.0.0.1:6379>
      ````

      可以看到客户端一创建的`键值对`已经被删除，所以其他客户端可以获取到锁

    * 客户端二获取锁

      ```java
      我获取到了锁 。。。。。。。。。。客户端二唯一值 0ff761b2-280a-4371-9c7c-7df4d681e7b2
      ```

      ````
      127.0.0.1:6379> get DISTRIBUTED_LOCK
      "0ff761b2-280a-4371-9c7c-7df4d681e7b2"
      127.0.0.1:6379>
      ````

      此时可以看到，redis中已经创建出新的`键值对`的值， value中与客户端二唯一值一致

    * 客户端一获取锁

      ```
      获取锁失败，锁已经被其他客户端持有。。。。
      ```

      此时，客户端一不能获取锁

  * 总结

    * 以上则为Java基于单节点Redis实现分布式锁的简单demo，代码还存在诸多不足，基本实现思路是正确的。

#### 2.基于多个Redis实现分布式锁

* 为什么使用多个Redis节点实现分布式锁
  * 提高可靠性
  * 单节点Redis如果出现故障，会影响业务正常的执行
  
* 实现思路

  * 分布式锁算法 Redlock

    ````json
    Redlock 算法的基本思路，是让客户端和多个独立的 Redis 实例依次请求加锁，如果客户端能够和半数以上的实例成功地完成加锁操作，那么我们就认为，客户端成功地获得分布式锁了，否则加锁失败。这样一来，即使有单个 Redis 实例发生故障，因为锁变量在其它实例上也有保存，所以，客户端仍然可以正常地进行锁操作，锁变量并不会丢失
    ````

  * 大致流程

    * **第一步 客户端获取当前时间**

      为后面计算是否能够加锁成功做准备

    * **第二步 客户端按顺序依次向 N 个 Redis 实例执行加锁操作**

      * 此处的加锁操作与单Redis节点加锁操作一样，使用SET命令，上 NX，EX/PX 选项，以及客户端唯一标识，并且需要加上超时时间，保证Redlock算法能够正常进行
      * 如果超时时间内不能加锁成功，此时客户端可以与下一个Redis实例继续请求加锁，并且，加锁的超时时间要远远小于锁的有效时间，一般为几十毫秒（个人认为一方面是提高整个Redis集群的加锁效率，减少整个加锁过程的耗时）
      
    * **第三步  一旦客户端完成了和所有 Redis 实例的加锁操作，客户端就要计算整个加锁过程的总耗时**

      * 加锁成功的两个条件，必须全部满足

        ````hxml
        条件一：客户端从超过半数（大于等于 N/2+1）的 Redis 实例上成功获取到了锁；
        条件二：客户端获取锁的总耗时没有超过锁的有效时间。
        ````

      * 满足条件后，重新计算锁的有效期

        ````xml
        在满足了这两个条件后，我们需要重新计算这把锁的有效时间，计算的结果是锁的最初有效时间减去客户端为获取锁的总耗时。
        如果锁的有效时间已经来不及完成共享数据的操作了，我们可以释放锁，以免出现还没完成数据操作，锁就过期了的情况
        ````

      * 锁的释放

        ```xml
        锁的释放与单个Redis节点相同，使用Lua脚本释放锁即可
        ```

* 代码示例如下

