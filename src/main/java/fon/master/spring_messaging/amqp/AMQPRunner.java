/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.amqp;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author hachiko
 */
@Component
public class AMQPRunner implements CommandLineRunner{
    private final RabbitTemplate rabbitTemplate;
    private final AMQPReceiver receiver;

    public AMQPRunner(AMQPReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(AMQPConfig.queueName, "Hello from Rabbit");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
