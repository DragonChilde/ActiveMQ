package com.springboot.consumer.topic.Consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author Lee
 * @create 2019/8/16 11:17
 */
@Component
public class TopicConsume {

    @JmsListener(destination = "${mytopic}")
    public void receiveMsg(TextMessage textMessage) throws  Exception{
        System.out.println("****消费者Topic接收到的信息是:"+textMessage.getText());

    }
}
