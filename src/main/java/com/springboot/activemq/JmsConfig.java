package com.springboot.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by admin on 2017/8/21.
 */
@Configuration
@EnableJms
public class JmsConfig {

    @Value("${application.topicDestinationName1}")
    public String TOPIC1;

    @Value("${application.topicDestinationName2}")
    public String TOPIC2;

    @Value("${application.queueDestinationName1}")
    public String QUEUE1;

    @Value("${application.queueDestinationName2}")
    public String QUEUE2;

    @Bean
    public Topic topic1() {
        return new ActiveMQTopic(TOPIC1);
    }

    @Bean
    public Topic topic2() {
        return new ActiveMQTopic(TOPIC2);
    }

    @Bean
    public Queue queue1() {
        return new ActiveMQQueue(QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new ActiveMQQueue(QUEUE2);
    }


    /**
     * topic模式的ListenerContainer
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        ActiveMQConnectionFactory connectionFactor=new ActiveMQConnectionFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactor);
        return factory;
    }

    /**
     * queue模式的ListenerContainer
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        ActiveMQConnectionFactory connectionFactor=new ActiveMQConnectionFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactor);
        return factory;
    }
}
