package redis.baifukuan.lock.single;

import redis.clients.jedis.Jedis;

/**
 * 基于单个redis 节点实现分布式锁
 *
 */
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
        String result = jedis.set(lockKey, requestId);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
