package com.my.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author Lee
 * @create 2019/8/12 15:01
 */
@Service
public class SpringMQConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQConsumer producer = (SpringMQConsumer)ctx.getBean("springMQConsumer");
        String message = (String) producer.jmsTemplate.receiveAndConvert();
        System.out.println(message);

        System.out.println("消费者接收成功!");
    }
}
