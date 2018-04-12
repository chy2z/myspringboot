package com.springboot.activemq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver2 {

    private static Logger logger = LoggerFactory.getLogger(TopicReceiver2.class.getName());

	@JmsListener(destination = "${application.topicDestinationName2}",containerFactory="topicListenerFactory")
	public void receiveTopic(String text) {
		logger.info("===========主题消费者2：Topic接受的消息为==============:" + text);
	}

}
