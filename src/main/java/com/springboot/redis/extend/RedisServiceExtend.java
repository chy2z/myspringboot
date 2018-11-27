package com.springboot.redis.extend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Title: RedisServiceExtend
* @Description: redis 扩展
* @author chy
* @date 2018/5/8 8:27
*/
@Repository
public class RedisServiceExtend  {

    private static String redisCode = "utf-8";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 统计bit位为1的总数
     * @param key
     */
    public long bitCount(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                result = connection.bitCount(key.getBytes());
                return result;
            }
        });
    }

    /**
     * 批处理命令
     * @param callback
     * @return
     */
    public List<Object> multi(RedisCallback<List<Object>> callback) {
        return redisTemplate.execute(callback);
    }

    /**
     * 获取value
     * @param key
     * @return
     */
    public byte[] get(final String key){
        return redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] result;
                result = connection.get(key.getBytes());
                return result;
            }
        });
    }
}