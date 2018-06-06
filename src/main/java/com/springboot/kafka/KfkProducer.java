package com.springboot.kafka;

import com.springboot.model.KFKMessage;
import com.springboot.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
* @Title: KfkProducer
* @Description: kafka生产者
* @author chy
* @date 2018/6/6 14:30
*/
@Component
public class KfkProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static int index=1;

    public void sendMessage(){
        KFKMessage m = new KFKMessage();
        m.setId(System.currentTimeMillis());
        m.setMsg("kafka消息--->"+index++);
        m.setSendTime(new Date());
        kafkaTemplate.send("test", JsonUtil.writeValueAsString(m));
        System.out.println("发送消息:"+JsonUtil.writeValueAsString(m));
    }
}
