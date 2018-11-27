package com.springboot.model;

import java.io.Serializable;

/**
* @Title: SeObj
* @Description: 测试序列化
* @author chy
* @date 2018/9/4 23:55
*/
public class SeObj implements Serializable {

    String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}
