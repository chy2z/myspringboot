package com.springboot.controller;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Title: ElkController
* @Description: elasticsearch logstash kibana 版本号6.2.3
* @author chy
* @date 2018/4/18 14:39
*/
@RestController
@EnableAutoConfiguration
@RequestMapping("/elk")
public class ElkController {

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log")
    @ResponseBody
    public String log(HttpServletRequest request, HttpServletResponse response) {
        logger.info("测试日志");
        return "测试日志";
    }

}
