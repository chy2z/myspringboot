package com.springboot.activemq.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver1 {

    private static Logger logger = LoggerFactory.getLogger(QueueReceiver1.class.getName());

	@JmsListener(destination = "${application.queueDestinationName1}",containerFactory="queueListenerFactory")
	public void receiveQueue(String text) {
		logger.info("===========队列消费者1：Queue接受的消息为==============:" + text);
	}

	//如果多个消费者同时消费1个队列，QueueReceiver写多个
}
