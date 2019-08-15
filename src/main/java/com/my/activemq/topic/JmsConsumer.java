package com.my.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Lee
 * @create 2019/8/8 16:36
 */
public class JmsConsumer {
    //设置目的地址URL
    private static final String ACTIVE_URL = "tcp://120.77.237.175:61616";
    //设置队列名称
    private static final String TOPIC = "topic01";

    public static void main(String[] args) throws Exception {

        System.out.println("Topic 消费者02");
        //1创建连接工厂,按照给定的url地址,采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2通过连接工厂,获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3创建会话session
        //两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Topic topic = session.createTopic(TOPIC);
        //5创建消息消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);

        /**通过监听的方式来接收消息**/
        /**异步非阴塞方法监听器onMessage()**/
        /**订阅者或者接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器,当消息到达之后,系统自动调用
         监听器MessageListener的 onMessage(Message message)方法**/
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message){
                if(message!=null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("通过Topic Listener接收到的信息是： "+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();

        System.out.println("消息接收完毕=====================");


    }
}
