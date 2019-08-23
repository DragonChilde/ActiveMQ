package com.my.activemq.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * @author Lee
 * @create 2019/8/22 14:20
 */
public class JmsProduceAsync {

    //设置目的地址URL
    //开启异步投递第一种方式
    private static final String ACTIVE_URL = "tcp://120.77.237.175:61619?jms.useAsyncSend=true";

    //设置队列名称
    private static final String QUEUE = "queue_async";

    public static void main(String[] args) throws Exception {
        //1创建连接工厂,按照给定的url地址,采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //开启异步投递第二种方式
       // activeMQConnectionFactory.setUseAsyncSend(true);

        //2通过连接工厂,获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        //开启异步投递第三种方式
        //ActiveMQConnection activeMQConnection = (ActiveMQConnection) connection;
        //activeMQConnection.setUseAsyncSend(true);
        connection.start();
        //3创建会话session
        //两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE);
        //5创建消息生产者
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);

        //6通过使用messageProducer生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("async msg is " + i);//可以理解发送字符串
            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"----order id");
            String messageID = textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(messageID);
                    System.out.println("async send success!");
                }

                @Override
                public void onException(JMSException e) {
                    /**如果异步发送失败可以人工处理重发**/
                    System.out.println(messageID);
                    System.out.println("async send fail!");
                }
            });

        }

        //9.关闭资源
        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送完毕===============");


    }
}
