package com.my.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Lee
 * @create 2019/8/8 16:36
 */
public class JmsConsumerPersist {
    //设置目的地址URL
    private static final String ACTIVE_URL = "tcp://120.77.237.175:61619";
    //设置队列名称
    private static final String TOPIC = "topic_persistent";

    public static void main(String[] args) throws Exception {

        System.out.println("Topic 消费者02");
        //1创建连接工厂,按照给定的url地址,采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2通过连接工厂,获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("23");
        //3创建会话session
        //两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Topic topic = session.createTopic(TOPIC);
        //5创建持久化消息消费者
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark....");
        connection.start();
        Message message = subscriber.receive();
        while (message!=null){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("收到的持久他topic是:"+ textMessage.getText());
            message = subscriber.receive(4000L);
        }
        subscriber.close();
        session.close();
        connection.close();

        System.out.println("持久化消息接收完毕=====================");


    }
}
