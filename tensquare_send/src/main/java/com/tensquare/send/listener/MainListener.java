package com.tensquare.send.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class MainListener {

	@RabbitHandler
	public void sendMain(Map<String,String> message){
		System.out.println(message.get("code"));
		System.out.println(message.get("mobile"));
	}
}
