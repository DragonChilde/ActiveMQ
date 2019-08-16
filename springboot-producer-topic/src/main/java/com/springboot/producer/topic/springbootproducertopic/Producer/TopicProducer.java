package com.springboot.producer.topic.springbootproducertopic.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author Lee
 * @create 2019/8/16 11:48
 */
@Component
public class TopicProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    private Topic topic;

    public void sendTopicMsg(){
        jmsMessagingTemplate.convertAndSend(topic,"*************topic msg is:"+ UUID.randomUUID().toString().substring(0,6));
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMsgBySchedule(){
        jmsMessagingTemplate.convertAndSend(topic,"*************topic schedule msg is:"+ UUID.randomUUID().toString().substring(0,6));
    }
}
