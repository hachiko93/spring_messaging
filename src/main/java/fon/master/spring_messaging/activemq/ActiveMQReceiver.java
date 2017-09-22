/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.master.spring_messaging.activemq;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author hachiko
 */
@Component
public class ActiveMQReceiver {
    @JmsListener(destination = "mailbox")
    public void receiveMessage(String message) {
        System.out.println("Received at " + new SimpleDateFormat("dd.MM.yy hh:mm:ss").format(new Date()) 
                + " new message: " + message);
    }
}
