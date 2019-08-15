package com.my.springboot.springbootproducer.Config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @create 2019/8/15 11:43
 */
@Component   // 让spring 管理的注解，相当于spring 中在xml 中写了个bean
@EnableJms  // 开启jms 适配
public class MyBean {

    @Value("${myqueue}")
    private String myQueue;     // 注入配置文件中的 myqueue

    @Bean
    public ActiveMQQueue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
