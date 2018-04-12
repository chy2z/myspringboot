package com.springboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/8/8.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/my")
public class HelloController {

    /**
     * http://127.0.0.1:{server.port}{server.context-path}/my/index
     * http://127.0.0.1:8080/my/index
     *  @CrossOrigin(origins = "*", maxAge = 3600) 必须jdk1.8
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response){
        String type = (String) request.getParameter("type");
        String name = (String) request.getParameter("name");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        System.out.println("类型：" + type);
        System.out.println("姓名:" + name);
        return "欢迎";
    }
}
