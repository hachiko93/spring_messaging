/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging;

import fon.master.spring_messaging.kafka.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

/**
 *
 * @author hachiko
 */
@SpringBootApplication
@EnableJms
public class App {
    
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending a message.");
        jmsTemplate.convertAndSend("mailbox", "Hello World");
        
        context.getBean(KafkaSender.class).send("helloworld.t", "Hello Boot!");
        System.out.println("Kafka message sent");
    }
}
