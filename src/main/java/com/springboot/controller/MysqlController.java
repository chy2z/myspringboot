package com.springboot.controller;

import com.springboot.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/8/10.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/mysql")
public class MysqlController {

    @Autowired
    MysqlService mysqlService;

    /**
     * http://127.0.0.1:{server.port}{server.context-path}/mysql/update
     * http://127.0.0.1:8080/mysql/update
     * @return
     */
    @RequestMapping("/update")
    public String update(){

        mysqlService.update();

        return "update";
    }

    /**
     * http://127.0.0.1:{server.port}{server.context-path}/mysql/list
     * http://127.0.0.1:8080/mysql/list
     * @return
     */
    @RequestMapping("/list")
    public String list(){

        mysqlService.queryForList();

        return "list";
    }
}
