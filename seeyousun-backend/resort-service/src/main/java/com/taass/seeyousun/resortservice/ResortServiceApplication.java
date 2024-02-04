package com.taass.seeyousun.resortservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResortServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResortServiceApplication.class, args);
    }

}
