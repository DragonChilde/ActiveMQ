package com.my.springboot.springbootproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class, args);
    }

}
