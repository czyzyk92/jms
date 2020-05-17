package com.example.demo.service;

import com.example.demo.model.Page;
import com.example.demo.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class PageService {

    @Autowired
    private PageRepository pageRepository;

    public Page savePage(String url) {
        byte[] bytes = downloadPage(url);
        Page page = new Page(url, bytes);
        return pageRepository.save(page);
    }

    public byte[] downloadPage(String pageUrl) {
        URL url = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;

        try {
            url = new URL(pageUrl);
            is = url.openStream();
            byte[] bytes = new byte[4096]; // todo dynamiczny rozmiar
            int n;

            while ((n = is.read(bytes)) > 0) {
                baos.write(bytes, 0, n);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return baos.toByteArray();
    }

    public Page getPage(String url) {
        return pageRepository.findByUrl(url);
    }
}
