package com.springboot.test;

import com.alibaba.fastjson.JSONObject;
import com.springboot.redis.extend.RedisServiceExtend;
import com.springboot.util.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @Title: DemoRedisSerializableObjectTest
* @Description: redis序列化测试
* @author chy
* @date 2018/9/4 23:17
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRedisSerializableObjectTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    RedisServiceExtend redisServiceExtend;


    @Test
    public void runSerizlize() {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                //SeObj seObjct=new SeObj();
                //seObjct.setTip("tip");
                //byte[] bytes= SerializeUtil.Serizlize(seObjct);
                //connection.set("seObjct".getBytes(), bytes);
                return result;
            }
        });
    }

    @Test
    public void runDerizlize() {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                byte[]  bytes= connection.get("seObjct".getBytes());
                Object object= SerializeUtil.Deserialize(bytes);
                System.out.println(JSONObject.toJSONString(object));
                return result;
            }
        });
    }

}
