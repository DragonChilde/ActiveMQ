package com.my.springboot.springbootproducer.Produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @author Lee
 * @create 2019/8/15 11:41
 */
@Component
public class QueueProduce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;


    public void produceMessage(){
        jmsMessagingTemplate.convertAndSend(queue,UUID.randomUUID().toString().substring(0,6));
    }
}
