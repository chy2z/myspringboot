package com.springboot.test;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
* @Title: SpringBeanLifeCycleTest
* @Description: spring bean 的生命周期
* @author chy
* @date 2018/4/26 16:34
*/
@Component
public class SpringBeanLifeCycleTest implements BeanNameAware,BeanFactoryAware {

    public static String beanName;


    /**
      在Spring 2.5之后，开发者有三种选择来控制Bean的生命周期行为：,
      1）InitializingBean和DisposableBean回调接口
      2）自定义的init()以及destroy方法
      3）使用@PostConstruct以及@PreDestroy注解
     */

     @PostConstruct
     public void init(){
         System.out.println("初始化");
     }

     @PreDestroy
     public void destroy(){
         System.out.println("销毁");
     }

    /**
     * BeanFactoryAware
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    /**
     * BeanNameAware
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        SpringBeanLifeCycleTest.beanName=s;
        System.out.println("setBeanName:"+beanName);
    }

}
