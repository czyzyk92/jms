package com.example.demo.controller;

import com.example.demo.client.JmsClient;
import com.example.demo.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class WebController {

    @Autowired
    JmsClient jsmClient;

    @Autowired
    PageService pageService;

    @RequestMapping(value = "/savePage")
    public String savePage(@RequestParam("url") String url) {
        pageService.savePage(url);
        return "We saved page successful";
    }

    @RequestMapping(value = "/produce")
    public String produce(@RequestParam("msg") String msg) {
        jsmClient.send(msg);
        return "We added request to queue";
    }

    @RequestMapping(value = "/receive")
    public void receive(HttpServletResponse response, @RequestParam("url") String url) throws IOException {
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = jsmClient.receive(url);
        outStream.write(buffer);
        outStream.close();
    }
}
