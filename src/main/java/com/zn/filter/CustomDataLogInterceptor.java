/**
 * Created by zhounan on 2015/12/5.
 */
package com.zn.filter;

import com.zn.until.AnnotationUtil;
import com.zn.until.annotation.TableName;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })
})
public class CustomDataLogInterceptor implements Interceptor {
 
    private Properties properties;
    private final static Logger logger = LoggerFactory.getLogger(CustomDataLogInterceptor.class);

    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        long start = System.currentTimeMillis();
         returnValue = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = (end - start);
        String username=null;
        //获取表名称
        String methodPath= mappedStatement.getId();
        String strClass = getClassName(methodPath);
        String strMethod = getMethod(methodPath);
        try {
            Class targetClass = Class.forName(strClass);
                String strTableName = AnnotationUtil.loadVlaue(TableName.class, "value", strClass, strMethod);
            strTableName=strTableName.equals("")?AnnotationUtil.loadValue(TableName.class,"value",strClass):strTableName;

            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            logger.debug(showSql(configuration,boundSql));

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return returnValue;
    }


    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "null";
            }
 
        }
        return value;
    }

    /**
     * 获取sql语句
     * @param configuration
     * @param boundSql
     * @return
     */
    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
 
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
 
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
 
    public void setProperties(Properties properties0) {
        this.properties = properties0;
    }

    ///根据方法路径名称 获取类名
    private String getClassName(String strName){
        String[] list=strName.split("\\.");
        String result="";
        for(int i=0;i<list.length-1;i++){
            result+=list[i]+".";
        }
        return result.substring(0,result.length()-1);
    }
    ///根据方法路径 获取方法名
    private String getMethod(String strName){
        String[] list=strName.split("\\.");
        return list[list.length-1];
    }

    private Method getMethodByClassAndMethodName(Class methodClass,String strMethod) {
        Method m[] = methodClass.getDeclaredMethods(); // 取得全部的方法
        for (int i = 0; i < m.length; i++) {
            String mod = Modifier.toString(m[i].getModifiers()); // 取得访问权限
            String metName = m[i].getName(); // 取得方法名称
            Class<?> ret = m[i].getReturnType();// 取得返回值类型
            Class<?> param[] = m[i].getParameterTypes(); // 得到全部的参数类型
            Class<?> exc[] = m[i].getExceptionTypes(); // 得到全部的异常
            if (metName.equals(strMethod)){
                return m[i];
            }
        }
        return null;
    }


}