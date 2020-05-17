package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(name = "content")
    private byte[] content;

    public Page() {
    }

    public Page(String url, byte[] bytes) {
        this.url = url;
        this.content = bytes;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public byte[] getContent() {
        return content;
    }

}
