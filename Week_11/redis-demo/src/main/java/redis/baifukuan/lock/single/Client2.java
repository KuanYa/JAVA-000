package redis.baifukuan.lock.single;

import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * 模拟客户端
 */
public class Client2 {
    private static final String RANDOM_UUID = UUID.randomUUID().toString();

    public static void main(String[] args) {
        // 模拟一个客户端获取锁
        Jedis jedis = new Jedis("localhost", 6379);
        // 释放锁，如果不能释放，则证明锁机制没有问题， 不会出现误释放锁的情况
        jedis.eval(GetLock.SCRIPT_UNLOCK, 1, GetLock.LOCK_KEY, RANDOM_UUID);
        // 每一个客户端生成一个随机数，作为value值，释放锁是根据唯一值判断，避免错误释放锁
        boolean flag = GetLock.tryGetDistributedLock(jedis, GetLock.LOCK_KEY, RANDOM_UUID, 1000000);
        try {
            if (flag) {
                // 代表获取到锁，可以执行后面你的操作
                // TODO
                System.out.println("我获取到了锁 。。。。。。。。。。" + "客户端二唯一值 " + RANDOM_UUID);
            } else {
                System.out.println("获取锁失败，锁已经被其他客户端持有。。。。");
            }
        } finally {
            // jedis.eval(GetLock.SCRIPT_UNLOCK, 1, GetLock.LOCK_KEY, RANDOM_UUID);
        }
    }
}
