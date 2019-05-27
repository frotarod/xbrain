package com.xbrain.loja;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class XbrainApplication {

	public final static String SFG_MESSAGE_QUEUE = "delivery-message-queue";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, false);
	}


	public static void main(String[] args) {
		SpringApplication.run(XbrainApplication.class, args);
	}
	
	 
}
