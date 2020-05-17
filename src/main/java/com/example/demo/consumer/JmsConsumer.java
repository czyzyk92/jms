package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.OutputStream;

@Component
public class JmsConsumer {
    @Autowired
    JmsTemplate jmsTemplate;

    public byte[] receive(String url) {
        return (byte[]) jmsTemplate.receiveAndConvert(url);
    }
}
