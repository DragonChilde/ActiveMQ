package com.springboot.producer.topic.springbootproducertopic;

import com.springboot.producer.topic.springbootproducertopic.Producer.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProducerTopicApplicationTests {

    @Resource
    private TopicProducer topicProducer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendTopicMsg(){
        topicProducer.sendTopicMsg();
    }

}
