package com.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void InterProcessMutex() {

        System.out.println("InterProcessMutex。。。。。。");

        ZookeeperLockTest.InterProcessMutex();

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
    }

    @Test
    public void InterProcessReadWriteLock() {
        System.out.println("InterProcessReadWriteLock。。。。。。");

        ZookeeperLockTest.InterProcessReadWriteLock();

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
    }

    @Test
    public void InterProcessSemaphoreV2() {
        System.out.println("InterProcessSemaphoreV2。。。。。。");

        ZookeeperLockTest.InterProcessSemaphoreV2();

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
    }

    @Test
    public void DistributedBarrier(){
        System.out.println("DistributedBarrier。。。。。。");

        ZookeeperLockTest.DistributedBarrier();

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
    }

    @Test
    public void DistributedDoubleBarrier(){
        System.out.println("DistributedDoubleBarrier。。。。。。");

        ZookeeperLockTest.DistributedDoubleBarrier();

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
    }

}
