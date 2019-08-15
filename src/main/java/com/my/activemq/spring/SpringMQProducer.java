package com.my.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author Lee
 * @create 2019/8/12 15:01
 */
@Service
public class SpringMQProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQProducer producer = (SpringMQProducer)ctx.getBean("springMQProducer");
        producer.jmsTemplate.send((session) -> {

            TextMessage textMessage = session.createTextMessage("Spring整合ActiveMQ发送消案例3for listener");
            return textMessage;
        });

        System.out.println("生产者信息发送成功!");
    }
}
