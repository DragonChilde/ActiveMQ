package com.my.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Lee
 * @create 2019/8/7 16:31
 */
public class JmsConsumerTx {
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
        Session session = connection.createSession(true,Session.CLIENT_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE);
        //5创建消息消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        /**接收方式一**/
        /**同步阻塞receive()订阅者或接收者调用MessageConsumer的receive()方法来接收消息,receive方法在能够接收到消息之前(或超时之前)将一直阻塞**/
        Message message = messageConsumer.receive();
            while (message!=null) {
                TextMessage textMessage = (TextMessage)message;

                System.out.println("从中间件接收到的信息是 " + textMessage.getText());
                  // message.acknowledge();
                 message = messageConsumer.receive(4000L);


            }

        session.commit();
        messageConsumer.close();
        session.close();
        connection.close();

        System.out.println("消息接收完毕=====================");


    }
}
