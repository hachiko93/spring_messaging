/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.test;

import fon.master.spring_messaging.amqp.AMQPConfig;
import fon.master.spring_messaging.amqp.AMQPReceiver;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author hachiko
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AMQPConfig.class)
@SpringBootTest
public class AMQPTest {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AMQPReceiver receiver;
    
    @Test
    public void test() {
        try {
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(AMQPConfig.queueName, "Hello from Rabbit");
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
}
