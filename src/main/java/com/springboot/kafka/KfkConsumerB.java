package com.springboot.kafka;

import com.springboot.model.KFKMessage;
import com.springboot.util.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
* @Title: KfkConsumer
* @Description: kafka消费者
* @author chy
* @date 2018/6/6 14:33
*/
@Component
public class KfkConsumerB {

    /**
     * 接收test主题消息
     * 如果所有的消费者的组号都一样，那么从主题接收消息会随机选择一个消费者，就相当于队列。
     * 如果所有的消费者的组号不一样，相当于主题
     *
     * groupId 在 1.1.6 版本中不能配置
     *
     * @param content
     */
    @KafkaListener(topics = "test",groupId = "myGroup")
    public void processMessage(String content) {
        KFKMessage m = JsonUtil.jsonToBean(content, KFKMessage.class);
        System.out.println("B接收消息:" + content);
    }
}
