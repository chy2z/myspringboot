package com.springboot.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
* @Title: ZookeeperLockTest
* @Description: Zookeeper 锁测试
* @author chy
* @date 2018/4/13 11:19
*/

public class ZookeeperLockTest {

    /**
     * 可以重入锁
     * 只有等前一个获取锁的线程释放锁后，下一个线程才能获取锁
     Thread[Thread-20,5,main]  acquire  lock
     Thread[Thread-20,5,main]  release  lock
     Thread[Thread-19,5,main]  acquire  lock
     Thread[Thread-19,5,main]  release  lock
     */
    public static void InterProcessMutex() {

        for (int i = 0; i <= 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));

                    client.start();

                    //指定锁路径
                    String lockPath = "/zkLockRoot/lock_1";

                    //创建锁，为可重入锁，即是获锁后，还可以再次获取，本例以此为例
                    InterProcessMutex typeLock = new InterProcessMutex(client, lockPath);

                    //创建锁，为不可重入锁，即是获锁后，不可以再次获取，这里不作例子，使用和重入锁类似
                    //InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, lockPath);
                    while (true) {
                        try {
                            try {
                                //获取锁
                                typeLock.acquire();
                                System.out.println(Thread.currentThread() + "  acquire lock");
                                Thread.sleep(5000);
                            } catch (Exception e) {
                            } finally {
                                //释放锁
                                typeLock.release();
                                System.out.println(Thread.currentThread() + "  release lock");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }).start();
        }
    }


    /**
     * 读写锁
     * 可以同时有多个线程获取读锁，但同时只能有一个线程获取写锁
     Thread[Thread-15,5,main]  acquire read lock
     Thread[Thread-17,5,main]  acquire read lock
     Thread[Thread-17,5,main]  release read lock
     Thread[Thread-15,5,main]  release read lock
     Thread[Thread-21,5,main]  acquire write lock
     Thread[Thread-21,5,main]  release write lock
     Thread[Thread-14,5,main]  acquire read lock
     Thread[Thread-14,5,main]  release read lock
     Thread[Thread-20,5,main]  acquire write lock
     Thread[Thread-20,5,main]  release write lock
     Thread[Thread-19,5,main]  acquire write lock
     Thread[Thread-19,5,main]  release write lock
     */
    public static void InterProcessReadWriteLock(){
        for (int i = 0; i <= 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));

                    client.start();

                    //指定锁路径
                    String lockPath = "/zkLockRoot/lock_1";

                    //创建读写锁
                    InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);

                    //获取读锁
                    InterProcessMutex readLock = lock.readLock();

                    while (true) {
                        try {
                            try {
                                //获取锁
                                readLock.acquire();
                                System.out.println(Thread.currentThread() + "  acquire read lock");
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            } finally {
                                //释放锁
                                readLock.release();
                                System.out.println(Thread.currentThread() + "  release read lock");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }).start();
        }

        for (int i = 0; i <= 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));

                    client.start();

                    //指定锁路径
                    String lockPath = "/zkLockRoot/lock_1";

                    //创建读写锁
                    InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);

                    //获取读锁
                    InterProcessMutex writeLock = lock.writeLock();

                    while (true) {
                        try {
                            try {
                                //获取锁
                                writeLock.acquire();
                                System.out.println(Thread.currentThread() + "  acquire write lock");
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            } finally {
                                //释放锁
                                writeLock.release();
                                System.out.println(Thread.currentThread() + "  release write lock");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }).start();
        }
    }


    /**
     * 信息量
     * 即同时最多只能允许指定数量的线程访问临界资源
     Thread[Thread-17,5,main]  acquire semaphore lock
     Thread[Thread-16,5,main]  acquire semaphore lock
     Thread[Thread-15,5,main]  acquire semaphore lock
     Thread[Thread-17,5,main]  release semaphore lock
     Thread[Thread-16,5,main]  release semaphore lock
     Thread[Thread-15,5,main]  release semaphore lock
     Thread[Thread-13,5,main]  acquire semaphore lock
     Thread[Thread-14,5,main]  acquire semaphore lock
     Thread[Thread-18,5,main]  acquire semaphore lock
     Thread[Thread-13,5,main]  release semaphore lock
     Thread[Thread-14,5,main]  release semaphore lock
     Thread[Thread-18,5,main]  release semaphore lock
     */
    public static void InterProcessSemaphoreV2(){
        for (int i = 0; i <= 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                    client.start();
                    //指定锁路径
                    String lockPath = "/zkLockRoot/lock_1";
                    //信号量
                    InterProcessSemaphoreV2 semaphoreLock = new InterProcessSemaphoreV2(client, lockPath,3);

                    while (true) {
                        try {
                            //获取锁
                            Lease lease =semaphoreLock.acquire();
                            try {
                                System.out.println(Thread.currentThread() + "  acquire semaphore lock");
                                Thread.sleep(3000);
                            } catch (Exception e) {
                            } finally {
                                //释放锁
                                semaphoreLock.returnLease(lease);
                                System.out.println(Thread.currentThread() + "  release semaphore lock");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }).start();
        }
    }


    /**
     * 分布式栅栏
     * 设置栅栏它会阻塞所有节点上的等待进程，当条件满足时，移除栅栏，所有等待的线程将继续执行：
     Thread[Thread-13,5,main]  setBarrier
     Thread[Thread-18,5,main]  rearch barrier, waiting
     Thread[Thread-17,5,main]  rearch barrier, waiting
     Thread[Thread-15,5,main]  rearch barrier, waiting
     Thread[Thread-14,5,main]  rearch barrier, waiting
     Thread[Thread-16,5,main]  rearch barrier, waiting
     Thread[Thread-13,5,main]  removeBarrier
     Thread[Thread-14,5,main]  leave barrier, finish
     Thread[Thread-16,5,main]  leave barrier, finish
     Thread[Thread-18,5,main]  leave barrier, finish
     Thread[Thread-15,5,main]  leave barrier, finish
     Thread[Thread-17,5,main]  leave barrier, finish
     */
    public static void DistributedBarrier(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建zookeeper客户端
                CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                client.start();
                //指定锁路径
                String lockPath = "/examples/barrier";
                //创建分布式栅栏
                DistributedBarrier barrier = new DistributedBarrier(client, lockPath);
                try {
                    barrier.setBarrier();
                    System.out.println(Thread.currentThread() + "  setBarrier");
                    Thread.sleep(10000);
                    barrier.removeBarrier();
                    System.out.println(Thread.currentThread() + "  removeBarrier");
                } catch (Exception e) {

                }
            }
        }).start();

        for (int i = 1; i <= 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                    client.start();
                    //指定锁路径
                    String lockPath = "/examples/barrier";
                    //创建分布式栅栏
                    DistributedBarrier barrier = new DistributedBarrier(client, lockPath);
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread() + "  rearch barrier, waiting");
                        barrier.waitOnBarrier();
                        System.out.println(Thread.currentThread() + "  leave barrier, finish ");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }).start();
        }
    }

    /**
     * 双栅栏
     * 双栅栏允许指定数量的客户端在计算的开始和结束时同步。
     * 当指定数量的进程加入到双栅栏时，进程开始计算，当计算全部完成时，一起离开栅栏
     Thread[Thread-17,5,main]  rearch barrier, enter
     Thread[Thread-15,5,main]  rearch barrier, enter
     Thread[Thread-14,5,main]  rearch barrier, enter
     Thread[Thread-16,5,main]  rearch barrier, enter
     Thread[Thread-13,5,main]  rearch barrier, enter
     Thread[Thread-13,5,main]  rearch barrier, work
     Thread[Thread-14,5,main]  rearch barrier, work
     Thread[Thread-15,5,main]  rearch barrier, work
     Thread[Thread-16,5,main]  rearch barrier, work
     Thread[Thread-17,5,main]  rearch barrier, work
     Thread[Thread-17,5,main]  leave barrier, leave
     Thread[Thread-14,5,main]  leave barrier, leave
     Thread[Thread-16,5,main]  leave barrier, leave
     Thread[Thread-13,5,main]  leave barrier, leave
     Thread[Thread-15,5,main]  leave barrier, leave
     */
    public static void DistributedDoubleBarrier(){
        for (int i = 1; i <= 5; i++) {
            final int sleep=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建zookeeper客户端
                    CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                    client.start();
                    //指定锁路径
                    String lockPath = "/examples/barrier";
                    //创建分布式栅栏
                    DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, lockPath,5);
                    try {
                        System.out.println(Thread.currentThread() + "  rearch barrier, enter");
                        barrier.enter();
                        Thread.sleep(1000*sleep);
                        System.out.println(Thread.currentThread() + "  rearch barrier, work");
                        barrier.leave();
                        System.out.println(Thread.currentThread() + "  leave barrier, leave ");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }).start();
        }
    }
}
