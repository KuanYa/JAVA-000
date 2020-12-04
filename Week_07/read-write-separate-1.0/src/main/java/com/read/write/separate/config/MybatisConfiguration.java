package com.read.write.separate.config;

import com.read.write.separate.intercepter.DynamicDataSourceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 注入拦截器
 *
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor(){
        DynamicDataSourceInterceptor dynamicDataSourceInterceptor = new DynamicDataSourceInterceptor();
        Properties properties = new Properties();
        dynamicDataSourceInterceptor.setProperties(properties);
        return dynamicDataSourceInterceptor;
    }
}
