package com.my.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * @author Lee
 * @create 2019/8/22 15:02
 */
public class JmsProduceSchedule {

    //设置目的地址URL
    private static final String ACTIVE_URL = "nio://120.77.237.175:61619";
    //private static final String ACTIVE_URL = "nio://120.77.237.175:61619";
    //设置队列名称
    private static final String QUEUE = "queue_schedule";

    public static void main(String[] args) throws Exception {
        //1创建连接工厂,按照给定的url地址,采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2通过连接工厂,获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3创建会话session
        //两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //4创建目的地，(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE);
        //5创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);

        long delay = 3 * 1000;
        long period = 4 * 1000;
        int repeat = 5;

        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //6通过使用messageProducer生产3条消息发送到MQ的队列里面
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg is " + i);//可以理解发送字符串
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);

            messageProducer.send(textMessage);
        }

        //9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送完毕===============");
    }
}
