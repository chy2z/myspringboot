package com.springboot.test;

import com.springboot.spi.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
* @Title: DemoSpiTest
* @Description: spi 测试
* @author chy
* @date 2018/6/25 13:49
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpiTest {
    @Test
    public void run() {
        ServiceLoader<Driver> matcher = ServiceLoader.load(Driver.class);
        Iterator<Driver> matcherIter = matcher.iterator();
        while (matcherIter.hasNext()) {
            Driver driver = matcherIter.next();
            System.out.println(driver.getClass().getName());
            driver.sayHello();
        }
    }
}
