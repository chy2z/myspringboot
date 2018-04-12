package com.springboot.activemq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver1 {

    private static Logger logger = LoggerFactory.getLogger(TopicReceiver1.class.getName());

	@JmsListener(destination = "${application.topicDestinationName1}",containerFactory="topicListenerFactory")
	public void receiveTopic(String text) {
		logger.info("===========主题消费者1：Topic接受的消息为==============:" + text);
	}

    //如果多个消费者同时消费1个主题，TopicReceiver写多个
}
