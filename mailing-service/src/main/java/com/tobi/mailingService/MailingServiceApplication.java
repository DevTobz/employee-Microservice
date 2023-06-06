package com.tobi.mailingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MailingServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(MailingServiceApplication.class,args);
    }
}
