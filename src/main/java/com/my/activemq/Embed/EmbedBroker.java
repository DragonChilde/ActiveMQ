package com.my.activemq.Embed;

import org.apache.activemq.broker.BrokerService;

/**
 * @author Lee
 * @create 2019/8/12 14:09
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
