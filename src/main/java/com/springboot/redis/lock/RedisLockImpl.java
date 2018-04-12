package com.springboot.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.TimeUnit;

/**
* @Title: RedisLockImpl
* @Description: 分布式锁实现
* @author chy
* @date 2018/2/28 22:17
*/
public class RedisLockImpl implements  RedisDistributionLock {

    /**
     * 加锁超时时间，单位毫秒， 即：加锁时间内执行完操作，如果未完成会有并发现象
     */
    private static final long LOCK_TIMEOUT = 5*1000;

    private static final Logger LOG = LoggerFactory.getLogger(RedisLockImpl.class);

    private StringRedisTemplate redisTemplate;

    public RedisLockImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     * 取到锁加锁，取不到锁一直等待知道获得锁
     * @param lockKey
     * @param threadName
     * @return
     */
    @Override
    public synchronized long lock(final String lockKey, String threadName) {
        LOG.info(threadName+"开始执行加锁");
        //循环获取锁
        while (true){
            //锁时间
            final Long lock_timeout = currtTimeForRedis()+ LOCK_TIMEOUT +1;
            if (redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    //定义序列化方式
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] value = serializer.serialize(lock_timeout.toString());
                    boolean flag = redisConnection.setNX(lockKey.getBytes(), value);
                    return flag;
                }
            })){
                //如果加锁成功
                LOG.info(threadName +"------->加锁成功-1");
                //设置超时时间，释放内存
                redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
                return lock_timeout;
            }
            else {
                //如果加锁失败

                //获取redis里面的时间
                String result = redisTemplate.opsForValue().get(lockKey);

                Long currt_lock_timeout_str = result==null?null:Long.parseLong(result);

                //判断是否为空，当加锁超时时间比现在时间小时，说明锁已经失效
                if (currt_lock_timeout_str != null && currt_lock_timeout_str < System.currentTimeMillis()){

                    //再次尝试获取锁

                    //获取上一个锁到期时间，并设置现在的锁到期时间
                    //getAndSet是同步执行，同时获取和设置值
                    Long old_lock_timeout_Str = Long.valueOf(redisTemplate.opsForValue().getAndSet(lockKey, lock_timeout.toString()));

                    //如果被其他线程设置了值，则条件判断无法执行
                    //如果old_lock_timeout_Str.equals(currt_lock_timeout_str)说明其他线程没有加锁
                    if (old_lock_timeout_Str != null && old_lock_timeout_Str.equals(currt_lock_timeout_str)){

                        LOG.info(threadName + "------->加锁成功-2");

                        //设置超时间，释放内存
                        redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);

                        //返回加锁时间
                        return lock_timeout;
                    }
                }
            }

            try {
                LOG.info(threadName +"等待加锁，睡眠100毫秒");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解锁
     * @param lockKey
     * @param lockValue
     * @param threadName
     */
    @Override
    public synchronized void unlock(String lockKey, long lockValue, String threadName) {
        LOG.info(threadName + "执行解锁==========");
        //正常直接删除 如果异常关闭判断加锁会判断过期时间

        //获取redis中设置的时间
        String result = redisTemplate.opsForValue().get(lockKey);

        Long currt_lock_timeout_str = result ==null?null:Long.valueOf(result);

        //如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
        if (currt_lock_timeout_str !=null && currt_lock_timeout_str == lockValue){
            redisTemplate.delete(lockKey);
            LOG.info(threadName + "解锁成功------------------");
        }
    }

    /**
     * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     * @return
     */
    @Override
    public long currtTimeForRedis(){
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }

}
