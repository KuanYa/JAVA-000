学习笔记



作业一：（**必做）**基于 Redis 封装分布式数据操作：

- 在 Java 中实现一个简单的分布式锁；
- 在 Java 中实现一个分布式计数器，模拟减库存。



#### 分布式锁

#### 1.基于单个 Redis 节点实现分布式锁

* 实现思路

  * Redis 可以使用键值对来保存锁变量，再接收和处理不同客户端发送的加锁和释放锁的操作请求

  * 因为 Redis 使用单线程处理请求，即使多个客户端同时请求，Redis 也会串行处理它们的请求

  * 基于Redis单命令操作和Lua脚本

  * `参考极客时间 Redis核心技术与实战 第30讲 如何实现Redis分布式锁`

  * `参考极客时间 Java性能调优实战 第41讲 如何设计更优的分布式锁`

    

* 代码实现流程如下（未完善）

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

    * 客户端一

      * 执行客户端一后，可以看到，redis中已经创建`键值对`,并且已经获取到了锁

        * **获取锁成功**

        ![image-20210103233237325](D:\Java-training-camp\JAVA-000\JAVA-000\Week_11\README.assets\image-20210103233237325.png)

        * **已经成功创建`键值对`**

        ![image-20210103233214880](D:\Java-training-camp\JAVA-000\JAVA-000\Week_11\README.assets\image-20210103233214880.png)

    * 客户端二

      * 执行客户端二时，可以看到，获取锁失败，因为客户端一还未释放锁

        ![image-20210103233531993](D:\Java-training-camp\JAVA-000\JAVA-000\Week_11\README.assets\image-20210103233531993.png)

      

