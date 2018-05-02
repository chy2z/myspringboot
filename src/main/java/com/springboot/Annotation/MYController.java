package com.springboot.Annotation;

import java.lang.annotation.*;

/**
* @Title: MYController
* @Description: 类注解
* @author chy
* @date 2018/4/27 10:12
*/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MYController {
    String value() default "";
}
