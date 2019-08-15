package com.my.springboot.consumer.springbootconsumer.Consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author Lee
 * @create 2019-08-15 23:02
 */
@Component
public class QueueConsume {



    @JmsListener(destination = "${myqueue}")
    public void receiveMsg(TextMessage textMessage) throws Exception{
        System.out.println("消费者接收到的消息是："+textMessage.getText());
    }
}
