package com.read.write.separate.datasource;

import com.read.write.separate.annotation.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 设置主从数据源变量
 *
 */
public class DynamicDataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    /**
     *  使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceType(String dsType)
    {
        log.info("切换到{}数据源", dsType);
        CONTEXT_HOLDER.set(dsType);
    }

    /**
     * 获得数据源的变量
     */
    public static String getDataSourceType()
    {
        String target = CONTEXT_HOLDER.get();
        if(Objects.isNull(target)){
            return DataSourceType.MASTER.name();
        }
        return target;
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType()
    {
        CONTEXT_HOLDER.remove();
    }
}
