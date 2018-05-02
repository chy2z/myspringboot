package com.springboot.Annotation;

import java.lang.annotation.*;

/**
* @Title: MYAliasFor
* @Description: 别名注解
* @author chy
* @date 2018/4/27 11:09
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MYAliasFor {
    @MYAliasFor("attribute")
    String value() default "";

    @MYAliasFor("value")
    String attribute() default "";

    Class<? extends Annotation> annotation() default Annotation.class;
}
