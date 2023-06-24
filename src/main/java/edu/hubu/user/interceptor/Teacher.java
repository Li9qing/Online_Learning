package edu.hubu.user.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: yanjunfeng
 * @Description: TODO
 * @Date: Created in 上午11:06 2023/6/19
 * @Description: 在需要教师权限的类或者方法上加上该注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Teacher {
}