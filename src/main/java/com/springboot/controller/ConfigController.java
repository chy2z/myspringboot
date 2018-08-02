package com.springboot.controller;

import com.springboot.properties.ImageTypeProperties;
import com.springboot.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/8/31.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/config")
public class ConfigController {

     @Resource
     ImageTypeProperties imageTypeProperties;

     @Autowired
     MyProperties myProperties;

    /**
     * http://127.0.0.1:8080/config/imagetype
     * @return
     */
    @RequestMapping("/imagetype")
    public String imagetype(){
        return imageTypeProperties.getPng();
    }

    /**
     * http://127.0.0.1:8080/config/myProperties
     * @return
     */
    @RequestMapping("/myProperties")
    public String myProperties(){
        return myProperties.getEmail();
    }

}