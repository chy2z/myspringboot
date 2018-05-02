package com.springboot.Annotation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
* @Title: MYRequestMapping
* @Description: 方法注解
* @author chy
* @date 2018/4/27 10:43 
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MYMapping
public @interface MYRequestMapping {
    String name() default "";

    String[] path() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};
}
