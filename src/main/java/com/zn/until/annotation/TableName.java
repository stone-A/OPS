package com.zn.until.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhounan on 2015/12/5.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableName {
    /**
     * 数据表名称注解，默认值为类名称
     * @return
     */
    public String  value() default "";
}
