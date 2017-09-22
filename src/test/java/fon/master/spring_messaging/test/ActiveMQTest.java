/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.test;

import fon.master.spring_messaging.activemq.ActiveMQConfig;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author hachiko
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ActiveMQConfig.class)
@SpringBootTest
public class ActiveMQTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void test() {
        this.jmsTemplate.convertAndSend("mailbox", "Hello, world!");
        this.jmsTemplate.setReceiveTimeout(10_000);
        String message = this.jmsTemplate.receiveAndConvert("mailbox").toString(); 
        System.out.println("Message: " + message);
        assertThat(message).isEqualTo("Hello, world!");
    }
}
