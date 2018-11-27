package com.springboot.test;

import com.springboot.redis.RedisDao;
import com.springboot.redis.extend.RedisServiceExtend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.BitSet;


/**
* @Title: DemoRedisIncrementTest
* @Description: redis 自增测试 线程安全
* @author chy
* @date 2018/5/5 16:25
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRedisIncrementTest {

    @Autowired
    RedisDao redisDao;

    @Autowired
    RedisServiceExtend redisServiceExtend;

    /**
     * redis 原子自增测试
     */
    @Test
    public void run() {

        String key="count";

        String table="Order";

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        long l= redisDao.increment(table,key);
                        System.out.println("t1----->"+l);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        long l= redisDao.increment(table,key);
                        System.out.println("t2----->"+l);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        long l= redisDao.increment(table,key);
                        System.out.println("t3----->"+l);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * redis bit测试
     */
    @Test
    public void bit() {
        //key
        String key1 = "20180501";

        //用户id
        int useId1 = 1;
        int useId2 = 2;
        int useId3 = 3;
        int useId4 = 4;
        int useId8 = 8;
        int useId9 = 9;
        int useId16 = 16;
        int useId20 = 20;


        //设置用户登录状态
        redisDao.setBit(key1, useId1, true);
        redisDao.setBit(key1, useId2, true);
        redisDao.setBit(key1, useId3, true);
        redisDao.setBit(key1, useId4, true);
        redisDao.setBit(key1, useId20, true);

        //二进制：0111 1000 0000 0000 0000 1000  16进制: 0x7800 0x08

        long login1 =redisServiceExtend.bitCount(key1);

        System.out.println("用户登录数量: " + login1);

        String key2 = "20180502";
        redisDao.setBit(key2, useId1, true);
        redisDao.setBit(key2, useId2, true);
        redisDao.setBit(key2, useId8, true);
        redisDao.setBit(key2, useId9, true);

        if(redisDao.getBit(key2,useId8)){
            System.out.println("useId8: is login" );
        }

        if(redisDao.getBit(key2,useId1)){
            System.out.println("useId1: is login");
        }

        long login2 =redisServiceExtend.bitCount(key2);

        System.out.println("用户登录数量: " + login2);
    }

    /**
     * java redis bit 测试
     */
    @Test
    public void javaBitSet() {
        BitSet bitSet = new BitSet();

        bitSet.set(1, true);
        bitSet.set(2, true);
        bitSet.set(8, true);

        int count = bitSet.cardinality();

        // 从右往左

        // 2进制 0000 0001 0000 0110

        // 16进制   0    1    0    6

        // byte[0]=6   byte[1]=1
        byte[] bytes= bitSet.toByteArray();

        System.out.println("bytes: " + bytes.length);

        System.out.println("bit 为 1的位置: " + count);


        //redis 二进制数据转换
        String key2 = "20180503";
        redisDao.setBit(key2, 1, true);
        redisDao.setBit(key2, 2, true);
        redisDao.setBit(key2, 8, true);

        String value= redisDao.getValue(key2);

        System.out.println("value: " + value);

        byte[] vBytes =redisServiceExtend.get(key2);

        BitSet rBitSet=fromByteArrayReverse(vBytes);

        byte[] rBytes= rBitSet.toByteArray();

        System.out.println("bytes: " + rBytes.length);

        System.out.println("bit 为 1的位置: " + rBitSet.cardinality());
    }

    /**
     * redis中bitmaps二进制存储的结构是从左到右
     * BitSets二进制存储的结构从右到左
     * @param bytes
     * @return
     */
    public static BitSet fromByteArrayReverse(final byte[] bytes) {
        final BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) != 0) {
                bits.set(i);
            }
        }
        return bits;
    }
}