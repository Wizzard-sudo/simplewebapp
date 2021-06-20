package com.mastery.java.task;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
public class Simplewebapp {

    public static void main(String[] args) {
        SpringApplication.run(Simplewebapp.class, args);
    }

    @Bean
    public Queue queueDelete() {
        return new ActiveMQQueue("simplewebapp.queue.delete");
    }

    @Bean
    public Queue queueSave() {
        return new ActiveMQQueue("simplewebapp.queue.save");
    }
}