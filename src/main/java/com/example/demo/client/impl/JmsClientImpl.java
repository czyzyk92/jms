package com.example.demo.client.impl;

import com.example.demo.client.JmsClient;
import com.example.demo.consumer.JmsConsumer;
import com.example.demo.producer.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmsClientImpl implements JmsClient {

    @Autowired
    JmsConsumer jmsConsumer;

    @Autowired
    JmsProducer jmsProducer;

    @Override
    public void send(String msg) {
        jmsProducer.send(msg);
    }

    @Override
    public byte[] receive(String url) {
        return jmsConsumer.receive(url);
    }

}
