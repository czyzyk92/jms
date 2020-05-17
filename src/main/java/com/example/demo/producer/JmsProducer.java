package com.example.demo.producer;

import com.example.demo.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.time.Instant;

@Component
public class JmsProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    PageService pageService;

    public void send(String url) {

        jmsTemplate.send(url, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bytesMessage = session.createBytesMessage();
                try {
                    bytesMessage.writeBytes(pageService.getPage(url).getContent());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                return bytesMessage;
            }
        });
    }
}