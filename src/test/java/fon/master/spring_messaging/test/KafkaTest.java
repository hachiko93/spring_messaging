/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.test;

import fon.master.spring_messaging.kafka.KafkaConsumerConfig;
import fon.master.spring_messaging.kafka.KafkaProducerConfig;
import fon.master.spring_messaging.kafka.KafkaReceiver;
import fon.master.spring_messaging.kafka.KafkaSender;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author hachiko
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, 
                      classes={KafkaConsumerConfig.class, KafkaProducerConfig.class})
@SpringBootTest
public class KafkaTest {

    private static String BOOT_TOPIC = "helloworld.t";

    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private KafkaReceiver listener;
    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, BOOT_TOPIC);

    @Test
    public void testReceive() throws Exception {
        kafkaSender.send(BOOT_TOPIC, "Hello Boot!");
        System.out.println("Kafka message sent");
        listener.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(listener.getLatch().getCount()).isEqualTo(0);
    }
}
