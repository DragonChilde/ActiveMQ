package com.springboot.producer.topic.springbootproducertopic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootProducerTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerTopicApplication.class, args);
    }

}
