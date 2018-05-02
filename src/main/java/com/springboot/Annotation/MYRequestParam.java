package com.springboot.Annotation;

import java.lang.annotation.*;

/**
 * 参数注解
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MYRequestParam {

    /**
     * name 和 value 是同一个
     * @return
     */
    @MYAliasFor("name")
    String value() default "";

    /**
     * name 和 value 是同一个
     * @return
     */
    @MYAliasFor("value")
    String name() default "";

    boolean required() default true;

    String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";
}
