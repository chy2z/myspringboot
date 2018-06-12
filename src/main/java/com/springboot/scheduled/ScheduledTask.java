package com.springboot.scheduled;

import com.springboot.activemq.queue.QueueSender;
import com.springboot.activemq.topic.TopicSender;
import com.springboot.kafka.KfkProducer;
import com.springboot.mongo.dao.AliYunDao;
import com.springboot.mongo.model.AliYun;
import com.springboot.redis.RedisDao;
import com.springboot.redis.lock.RedisLockImpl;
import com.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @Title: ScheduledTask
* @Description: 任务
* @author chy
* @date 2018/2/28 22:09
*/
@Component
/**
 * 异步注解
 */
    @EnableAsync
    public class ScheduledTask {

    boolean debug = false;

    @Autowired
    QueueSender queue;

    @Autowired
    TopicSender topic;

    @Autowired
    AliYunDao aliYunDao;

    @Autowired
    RedisDao redisDao;

    @Autowired
    KfkProducer kfkProducer;

    /**
     * MQ队列测试
     */
    @Async
    @Scheduled(fixedDelay = 50)
    public void sendMQQueueTest() {
        if (debug) {
            String message = "对列:" + DateUtil.formatDate(new Date());
            queue.send1(message);
            queue.send2(message);
        }
    }

    /**
     * MQ主题测试
     */
    @Async
    @Scheduled(fixedDelay = 50)
    public void sendMQTopicTest() {
        if (debug) {
            String message = "主题:" + DateUtil.formatDate(new Date());
            topic.send1(message);
            topic.send2(message);
        }
    }


    /**
     * Kafka主题测试
     */
    @Async
    @Scheduled(fixedDelay = 50)
    public void sendKafkaTopicTest() {
        if (debug) {
            kfkProducer.sendMessage();
        }
    }


    /**
     * mongo测试
     */
    @Async
    @Scheduled(fixedDelay = 10)
    public void insertMongo() {
        if (debug) {
            int index = 1;
            int row = 100;
            List<AliYun> batchList = new ArrayList<AliYun>();
            AliYun aly = null;
            while (row-- >= 0) {
                try {
                    aly = new AliYun();
                    aly.setFactoryNo(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
                    aly.setEq(index++);
                    aly.setPrice(200.45);
                    aly.setMoney(new BigDecimal("300.45"));
                    aly.setUpdateTime(new Date());
                    batchList.add(aly);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //logger.info("插入开始--->"+DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss.SSS"));
            aliYunDao.insertMulti(batchList);
            //logger.info("插入结束--->"+DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss.SSS"));
            batchList.clear();
            //logger.info("插入结束--->"+DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss.SSS"));
        }
    }

    /**
     * redis锁并发测试
     */
    @Async
    @Scheduled(fixedDelay = 300000)
    public void redisSetnx1() {
        if (debug) {
            RedisLockImpl redisLock = new RedisLockImpl(redisDao.getStringRedisTemplate());
            String key = "chyLock";
            String threadName = "lock-1";
            while (debug) {
                try {
                    long time = redisLock.lock(key, threadName);
                    Thread.sleep(100);
                    System.out.println(threadName + "：执行业务");
                    redisLock.unlock(key, time, threadName);
                    Thread.sleep(200);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    /**
     * redis锁并发测试
     */
    @Async
    @Scheduled(fixedDelay = 300000)
    public void redisSetnx2() {
        if (debug) {
            RedisLockImpl redisLock = new RedisLockImpl(redisDao.getStringRedisTemplate());
            String key = "chyLock";
            String threadName = "lock-2";
            while (debug) {
                try {
                    long time = redisLock.lock(key, threadName);
                    Thread.sleep(100);
                    System.out.println(threadName + "：执行业务");
                    redisLock.unlock(key, time, threadName);
                    Thread.sleep(200);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    /**
     * redis锁并发测试
     */
    @Async
    @Scheduled(fixedDelay = 300000)
    public void redisSetnx3() {
        if (debug) {
            RedisLockImpl redisLock = new RedisLockImpl(redisDao.getStringRedisTemplate());
            String key = "chyLock";
            String threadName = "lock-3";
            while (debug) {
                try {
                    long time = redisLock.lock(key, threadName);
                    Thread.sleep(100);
                    System.out.println(threadName + "：执行业务");
                    redisLock.unlock(key, time, threadName);
                    Thread.sleep(200);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
