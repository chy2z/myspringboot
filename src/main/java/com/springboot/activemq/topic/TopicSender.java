package com.springboot.activemq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class TopicSender {
	
	private final static Logger logger= LoggerFactory.getLogger(TopicSender.class);
	
    @Autowired
    JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Topic topic1;

    @Autowired
    private Topic topic2;

    /**
     * 发送一条消息到指定的主题
     * @param message 消息内容
     */
    public void send1(final String message){
        logger.info("===========主题:发送的消息为==============:"+message);
        this.jmsTemplate.convertAndSend(topic1, message);
    }

    /**
     * 发送一条消息到指定的主题
     * @param message 消息内容
     */
    public void send2(final String message){
        logger.info("===========主题:发送的消息为==============:"+message);
        this.jmsTemplate.convertAndSend(topic2, message);
    }
 
}