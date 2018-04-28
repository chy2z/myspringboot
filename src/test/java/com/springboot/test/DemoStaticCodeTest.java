package com.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @Title: DemoStaticCodeTest
* @Description: 静态代码块测试
* @author chy
* @date 2018/4/26 15:35
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoStaticCodeTest {

    @Test
    public void test1() {
         StaticCodeTest.main(null);
    }

    @Test
    public void test2() {
        new StaticChildTest();
        System.out.println( "==========================" );
        new StaticChildTest();
    }
}
