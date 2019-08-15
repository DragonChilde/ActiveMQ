package com.my.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Lee
 * @create 2019/8/7 15:35
 */
public class JmsProduceTx {
    //设置目的地址URL
   // private static final String ACTIVE_URL = "tcp://120.77.237.175:61616";
    private static final String ACTIVE_URL = "tcp://localhost:61616";
    //设置队列名称
    private static final String QUEUE = "queue01";


    public static void main(String[] args) throws Exception {
        //1创建连接工厂,按照给定的url地址,采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2通过连接工厂,获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3创建会话session
        //两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE);
        //5创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);
        //6通过使用messageProducer生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++) {
            //7创建消息,

            TextMessage textMessage = session.createTextMessage("msg is " + i);//可以理解发送字符串
//            textMessage.setStringProperty("vip","1");
//            textMessage.setJMSDeliveryMode();
//            textMessage.setJMSDestination();
//            textMessage.setJMSPriority();
//            textMessage.setJMSMessageID();
//            textMessage.setJMSExpiration();
            messageProducer.send(textMessage);
        }
//
//        MapMessage mapMessage = session.createMapMessage();
//        mapMessage.setString("key","value");
//        messageProducer.send(mapMessage);
        //9.关闭资源
        session.commit();
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送完毕===============");
    }
}
