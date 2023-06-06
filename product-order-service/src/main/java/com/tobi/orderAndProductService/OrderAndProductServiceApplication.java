package com.tobi.orderAndProductService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OrderAndProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderAndProductServiceApplication.class, args);
	}

}
