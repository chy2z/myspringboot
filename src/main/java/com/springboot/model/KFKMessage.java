package com.springboot.model;

import java.util.Date;

/**
* @Title: KFKMessage
* @Description: 测试发送kafka消息
* @author chy
* @date 2018/6/6 14:28
*/
public class KFKMessage {
    private Long id;
    private String msg;
    private Date sendTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
