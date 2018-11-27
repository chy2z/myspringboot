package com.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* @Title: MyProperties
* @Description: 加载自定义配置文件
* @author chy
* @date 2018/7/4 17:14
*/
@Component
@ConfigurationProperties(prefix="my")
@PropertySource("classpath:my.properties")
public class MyProperties {
    private  String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
