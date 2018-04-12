package com.springboot.activemq.queue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class QueueSender {
	
	private final static Logger logger= LoggerFactory.getLogger(QueueSender.class);
	
    @Autowired
    JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue1;

    @Autowired
    private Queue queue2;

    /**
     * 发送一条消息到指定的队列
     * @param message 消息内容
     */
    public void send1(final String message){
        logger.info("===========Queue发送的消息为==============:"+message);
        this.jmsTemplate.convertAndSend(queue1, message);
    }

    /**
     * 发送一条消息到指定的队列
     * @param message 消息内容
     */
    public void send2(final String message){
        logger.info("===========Queue发送的消息为==============:"+message);
        this.jmsTemplate.convertAndSend(queue2, message);
    }
 
}