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
                .addNodeAddress("redis://127.0.0.1:6379")
                .addNodeAddress("redis://127.0.0.1:6380")
                .addNodeAddress("redis://127.0.0.1:6381");
        return Redisson.create(config);
    }
}
