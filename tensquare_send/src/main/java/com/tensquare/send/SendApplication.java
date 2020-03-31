package com.tensquare.send;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendApplication.class);
	}
}
