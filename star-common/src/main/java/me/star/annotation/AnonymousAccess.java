package me.star.annotation;

import java.lang.annotation.*;

/**
 * @author Star Chou
 * @description 用于标记匿名访问方法
 * @create 2022/1/8 19:28
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
