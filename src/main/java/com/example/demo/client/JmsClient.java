package com.example.demo.client;

public interface JmsClient {
	void send(String msg);
	byte[] receive(String url);
}
