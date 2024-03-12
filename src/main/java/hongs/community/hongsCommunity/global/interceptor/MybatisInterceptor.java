package hongs.community.hongsCommunity.global.interceptor;


import hongs.community.hongsCommunity.global.util.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type= StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})
        ,@Signature(type = StatementHandler.class, method = "update", args = {Statement.class})
})
@Slf4j
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        HttpServletRequest request = WebUtil.nowRequest();
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        bindingSQL(request ,handler);
        return invocation.proceed();
    }

    public void bindingSQL(HttpServletRequest request, StatementHandler handler) throws NoSuchFieldException, IllegalAccessException {
        BoundSql boundSql = handler.getBoundSql();
        Object param = handler.getParameterHandler().getParameterObject();
        String sql = boundSql.getSql().replaceAll("\\s*(?=(\\r\\n|\\r|\\n))+", "");

        // if param null
        if(param == null) {
            setSqls(request, sql.replaceFirst("\\?", "''"));
        }

        // if param : int, long, float, double
        else if(param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
            String paramStr = formValue(param);
            setSqls(request, sql.replaceFirst("\\?", paramStr));
        }

        // if param : string
        else if(param instanceof String) {
            String paramStr = formValue(param);
            setSqls(request, sql.replaceFirst("\\?", "'" + paramStr + "'"));
        }

        // if param : map
        else if(param instanceof Map) {
            Map paramObjectMap = (Map) param;
            List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

            for(ParameterMapping mapping : paramMapping) {
                String key = mapping.getProperty();

                try {
                    Object value = null;
                    if(boundSql.hasAdditionalParameter(key)) {  // 동적 SQL로 인해 __frch_item_0 같은 파라미터가 생성되어 적재됨, additionalParameter로 획득
                        value = boundSql.getAdditionalParameter(key);
                    } else {
                        value = paramObjectMap.get(key);
                    }

                    String valueStr = formValue(value);
                    sql = sql.replaceFirst("\\?", valueStr);

                } catch (Exception e) {
                    sql = sql.replaceFirst("\\?", "#{" + key + "}");
                }
            }

            setSqls(request, sql);
        }

        // if param : class
        else {
            List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
            Class<? extends Object> paramClass = param.getClass();

            for(ParameterMapping mapping : paramMapping) {
                String key = mapping.getProperty();

                try{
                    Object value = null;
                    if(boundSql.hasAdditionalParameter(key)) {          // 동적 SQL로 인해 __frch_item_0 같은 파라미터가 생성되어 적재됨, additionalParameter로 획득
                        value = boundSql.getAdditionalParameter(key);
                    } else {
                        Field field = ReflectionUtils.findField(paramClass, key);
                        field.setAccessible(true);
                        value = field.get(param);
                    }

                    String valueStr = formValue(value);
                    sql = sql.replaceFirst("\\?", valueStr);

                }catch (Exception e) {
                    sql = sql.replaceFirst("\\?", "#{" + key + "}");
                }
            }
            setSqls(request, sql);
        }
    }

    private String formValue(Object o) {
        if(o == null) {
            return "*";
        }
        if(o instanceof String) {
            return String.format("'%s'", o.toString());
        }
        return o.toString();
    }

    private void setSqls(HttpServletRequest request, String sql) {
        StringBuilder sqls = getStringBuilder(request);
        sqls.append("\n        " + sql);
        sqls.append("\n =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
        request.setAttribute(LoggingInterceptor.MYBATIS_SQL_LOG, sqls);
    }

    public StringBuilder getStringBuilder(HttpServletRequest request){
        StringBuilder sqls = (StringBuilder) request.getAttribute(LoggingInterceptor.MYBATIS_SQL_LOG);
        if(sqls == null) {
            sqls = new StringBuilder();
        }
        return sqls;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) { }
}
