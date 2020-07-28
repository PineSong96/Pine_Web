package com.pine.admin.filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import com.pine.common.dto.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;


/**
 * 分页拦截器
 * @Author: Pine
 * @Date: 2019/4/4
 * @Email:771190883@qq.com
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObjet = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObjet.getValue("delegate.mappedStatement");
        //配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        //检查以ByPage结尾的id
        if (id.matches(".+ByPage$")) {
            BoundSql bounSql = statementHandler.getBoundSql();
            //原始的SQL语句
            String sql = bounSql.getSql();
            //查询总条数的SQL语句
            String countSql = "select count(1) from (" + sql + ") aliasForPage";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObjet.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            Page page = new Page();
            Map<?, ?> parameter = (Map<?, ?>) bounSql.getParameterObject();
            if (parameter != null) {

                page = (Page) parameter.get("page");
            }
            if (rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            //改造后带分页查询的SQL语句
            String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
//            String pageSql = sql + " limit " + page.getDbNumber() + " offset " + page.getDbIndex();
            metaObjet.setValue("delegate.boundSql.sql", pageSql);

        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }


    @Override
    public void setProperties(Properties properties) {

    }

}
