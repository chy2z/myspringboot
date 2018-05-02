package com.springboot.Annotation;

import java.lang.annotation.*;

/**
* @Title: MYAnnotation
* @Description: 注解说明含义
* @author chy
* @date 2018/4/27 10:23
*/

/**
 #注解运用的地方
 - ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 - ElementType.CONSTRUCTOR 可以给构造方法进行注解
 - ElementType.FIELD 可以给属性进行注解
 - ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 - ElementType.METHOD 可以给方法进行注解
 - ElementType.PACKAGE 可以给一个包进行注解
 - ElementType.PARAMETER 可以给一个方法内的参数进行注解
 - ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 */
@Target(ElementType.TYPE)

/**
 #注解的的存活时间
 - RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 - RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 - RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
 */
@Retention(RetentionPolicy.RUNTIME)

/**
 #子类没有被任何注解应用的话，那么这个子类就继承了超类的注解
 */
@Inherited

/**
 #将注解中的元素包含到 Javadoc 中去
 */
@Documented
public @interface MYAnnotation {
    public int id() default -1;
    public String msg() default "自定义注解";
}
