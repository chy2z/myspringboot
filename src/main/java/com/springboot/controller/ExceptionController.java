package com.springboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Title: ExceptionController
* @Description: 统一异常处理
* @author chy
* @date 2018/8/27 21:17
*/
@RestController
@EnableAutoConfiguration
@RequestMapping("/exception")
public class ExceptionController extends BaseController {


    @RequestMapping("/demo1")
    public String demo1() {
        int i = 0;
        int j = 1;
        j = j / i;
        return "demo1";
    }

    @RequestMapping("/demo2")
    public String demo2() {
        Object o = null;
        return o.toString();
    }

}