package com.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class MessageCreator implements CommandLineRunner {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final Queue queue;

    @Autowired
    public MessageCreator(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    @Override
    public void run(String... args) throws Exception {
        this.jmsMessagingTemplate.convertAndSend(this.queue, "This example text provided MessageCreator");
        System.out.println("Message has been put to queue by sender");
    }
}
