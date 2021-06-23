package com.kevin.dao.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author kevin
 * <p>
 * 自定义mybatis截器，将来可用来拦截 update语句是否提交,强制更新字段update_at
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class})})
public class DaoInterceptor implements Interceptor {
    private Properties properties = new Properties();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        BoundSql boundSql = statement.getBoundSql(args[1]);
        List<ParameterMapping> mappings = boundSql.getParameterMappings();
        mappings.stream().forEach(parameterMapping -> log.info("parameter:{}", parameterMapping));
        boolean isUpdate = mappings.stream()
                .anyMatch(parameterMapping -> Objects.equals(parameterMapping.getProperty(), "orderNO"));
        log.info("是否更新了orderNO字段:{}", isUpdate);
        String sql = boundSql.getSql();
        log.info("sql:{}", sql);
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
