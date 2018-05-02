package com.springboot.Annotation;

import java.lang.annotation.*;

/**
* @Title: MYAutowired
* @Description: 属性注解
* @author chy
* @date 2018/4/27 11:47
*/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MYAutowired {
    boolean required() default true;
}
