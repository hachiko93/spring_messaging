/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.kafka;

import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

/**
 *
 * @author hachiko
 */
@Component
public class KafkaReceiver {

    public final CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(topics = "helloworld.t")
    public void receive(ConsumerRecord<?, ?> record) {
        System.out.println("Message received with Kafka: " + record);
        countDownLatch.countDown();
    }

    public CountDownLatch getLatch() {
        return countDownLatch;
    }
}
