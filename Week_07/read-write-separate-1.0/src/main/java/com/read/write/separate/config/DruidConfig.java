package com.read.write.separate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.read.write.separate.annotation.DataSourceType;
import com.read.write.separate.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    /**
     * 注入主数据源
     *
     * @param druidProperties
     * @return
     */
	@ConfigurationProperties(prefix="spring.datasource.druid.master")
	@Bean
    @Primary
	public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return  druidProperties.dataSource(dataSource);

    }

    /**
     * 输入从数据源
     *
     * @param druidProperties
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.druid.slave")
    // @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return  druidProperties.dataSource(dataSource);

    }

    /**
     * 注入数据源实例
     *
     * @param masterDataSource
     * @param slaveDataSource
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(DataSource masterDataSource, DataSource slaveDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
