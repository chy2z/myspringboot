package com.springboot.model;

import java.util.Date;

/**
* @Title: EsModel
* @Description:  Elasticsearc 测试类
* @author chy
* @date 2018/4/25 15:43
*/
public class EsModel {

    private Object id;

    private String name;

    private int age;

    private Date date;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
