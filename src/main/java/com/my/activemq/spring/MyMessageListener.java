package com.my.activemq.spring;

import org.springframework.jmx.JmxException;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Lee
 * @create 2019/8/12 15:31
 */
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message != null && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                String text = textMessage.getText();
                System.out.println("监听器接收到的消息是："+text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
