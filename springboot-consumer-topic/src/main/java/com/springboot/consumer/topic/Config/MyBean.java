package com.springboot.consumer.topic.Config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @author Lee
 * @create 2019/8/16 11:06
 */
@Component
@EnableJms
public class MyBean {
    @Value("${mytopic}")
    private String myTopic;

    @Bean
    public ActiveMQTopic topic(){
        return new ActiveMQTopic(myTopic);
    }
}
