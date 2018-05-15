package com.springboot.redis.extend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
* @Title: RedisServiceExtend
* @Description: redis 扩展
* @author chy
* @date 2018/5/8 8:27
*/
@Repository
public class RedisServiceExtend  {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static String redisCode = "utf-8";

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
}