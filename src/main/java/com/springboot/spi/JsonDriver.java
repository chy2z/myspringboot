package com.springboot.spi;

/**
* @Title: JsonDriver
* @Description: json 驱动实现
* @author chy
* @date 2018/6/25 13:45
*/
public class JsonDriver implements Driver {

    @Override
    public void sayHello() {
        System.out.println("JsonDriver");
    }
}


