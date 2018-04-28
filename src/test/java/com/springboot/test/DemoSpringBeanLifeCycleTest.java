package com.springboot.test;

import com.springboot.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @Title: DemoSpringBeanLifeCycleTest
* @Description: bean 生命周期测试
* @author chy
* @date 2018/4/27 8:39
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringBeanLifeCycleTest {

    @Test
    public void test() {
        SpringBeanLifeCycleTest bean = SpringUtil.getBean("springBeanLifeCycleTest",SpringBeanLifeCycleTest.class);
        System.out.println(bean.beanName);
    }

}
