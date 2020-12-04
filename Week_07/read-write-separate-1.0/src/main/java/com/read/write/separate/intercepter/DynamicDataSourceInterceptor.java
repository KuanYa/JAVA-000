package com.read.write.separate.intercepter;

import com.read.write.separate.annotation.DataSourceType;
import com.read.write.separate.datasource.DynamicDataSource;
import com.read.write.separate.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * 定义Mybatis拦截器
 * 动态切换数据源
 *
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DynamicDataSourceInterceptor implements Interceptor {
    /**
     * 判断是插入还是增加还是删除之类的正则, u0020是空格
     */
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.update\\u0020.*";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断当前是否有实际事务处于活动状态 true 是
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        //获取sql的资源变量参数（增删改查的一些参数）
        Object[] objects = invocation.getArgs();
        //MappedStatement 可以获取到到底是增加还是删除 还是修改的操作
        MappedStatement mappedStatement = (MappedStatement) objects[0];
        //用来决定datasource的
        String lookupKey = DataSourceType.MASTER.name();

        if (!synchronizationActive){
            //当前的是有事务的====================Object[0]=org.apache.ibatis.mapping.MappedStatement@c028cc
            log.debug("当前的是有事务的====================Object[0]=" + objects[0]);
            //读方法,说明是 select 查询操作
            if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)){
                //如果selectKey 为自增id查询主键（select last_insert_id（）方法），使用主库，这个查询是自增主键的一个查询
                if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
                    //使用主库
                    lookupKey = DataSourceType.MASTER.name();
                } else {
                    //获取到绑定的sql
                    BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                    String sqlstr = boundSql.getSql();
                    //toLowerCase方法用于把字符串转换为小写,replaceAll正则将所有的制表符转换为空格
                    String sql = sqlstr.toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    //sql是这个===================：select top 1 * from cipentinfo where regno=?
                    log.debug("执行的sql是：{}",sql);
                    //使用sql去匹配正则，看他是否是增加、删除、修改的sql，如果是则使用主库
                    if (sql.matches(REGEX)){
                        lookupKey = DataSourceType.MASTER.name();
                    } else {
                        //从读库（从库），注意，读写分离后一定不能将数据写到读库中，会造成非常麻烦的问题
                        lookupKey = DataSourceType.SLAVE.name();
                    }
                }
            }
        } else {
            //非事务管理的用主库
            lookupKey = DataSourceType.MASTER.name();
        }

        log.debug("设置方法[{}] ues [{}] Strategy,SqlCommanType[{}]，sqlconmantype[{}] " ,mappedStatement.getId(), lookupKey, mappedStatement.getSqlCommandType().name(), mappedStatement.getSqlCommandType());
        //最终决定使用的数据源
        DynamicDataSourceContextHolder.setDataSourceType(lookupKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor){
            //如果是Executor 那就进行拦截
            return Plugin.wrap(target, this);
        } else {
            //否则返回本体
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
