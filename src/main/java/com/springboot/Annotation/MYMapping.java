package com.springboot.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Title: MYMapping
* @Description: 注解的注解
* @author chy
* @date 2018/4/27 11:15
*/
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MYMapping {

}
