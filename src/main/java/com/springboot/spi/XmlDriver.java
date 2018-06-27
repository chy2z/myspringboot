package com.springboot.spi;

/**
* @Title: XmlDriver
* @Description: xml 驱动实现
* @author chy
* @date 2018/6/25 13:46
*/
public class XmlDriver implements Driver {

    @Override
    public void sayHello() {
        System.out.println("XmlDriver");
    }
}
