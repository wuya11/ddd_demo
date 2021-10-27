package com.ddd.demo.annotation;

import java.lang.annotation.*;

/**
 * MyBatis-增强自定义注解
 *
 * @author wl 2021-5-28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Enhancer {

    /***
     * 是否自动计算分页的总数
     * @return 默认计算
     */
    boolean autoPageCount() default true;

}
