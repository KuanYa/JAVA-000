package redis.baifukuan.lock.multiple;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientDemo {

    /**
     * 获取客户端
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                .addNodeAddress("redis://192.168.199.216:6379")
                .addNodeAddress("redis://192.168.199.216:6380");
        return Redisson.create(config);
    }
}
