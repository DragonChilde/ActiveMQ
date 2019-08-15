package com.my.springboot.springbootproducer;

import com.my.springboot.springbootproducer.Produce.QueueProduce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProducerApplicationTests {

    @Resource
    private QueueProduce queueProduce;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testProduceSendMsg(){
        queueProduce.produceMessage();
    }
}
