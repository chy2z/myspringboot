package com.springboot.service;

import com.springboot.dao.MysqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/10.
 */
@Service
public class MysqlService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MysqlDao mysqlDao;

    public void update(){
        mysqlDao.update();
    }

    public void queryForList(){
        List<Map<String, Object>> ls=  mysqlDao.queryForList();

        for (Map<String, Object> map : ls){

            logger.info("name:"+map.get("name")+","+map.get("nickname"));

        }
    }
}
