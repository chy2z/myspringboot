package com.springboot.controller;

import com.springboot.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/8/10.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisDao redisDao;

    /**
     * http://127.0.0.1:8080/redis/put
     * @return
     */
    @RequestMapping("/put")
    public String put(){
        redisDao.putString("spboot","spring","1.11");
        redisDao.putString("spboot","redis","1.21");
        return "put";
    }

    /**
     * http://127.0.0.1:8080/redis/get
     * @return
     */
    @RequestMapping("/get")
    public String get(){
        return "get:"+redisDao.getString("spboot","redis");
    }
}
