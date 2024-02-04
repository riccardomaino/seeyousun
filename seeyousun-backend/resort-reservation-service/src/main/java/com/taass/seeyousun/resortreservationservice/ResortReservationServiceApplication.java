package com.taass.seeyousun.resortreservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResortReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResortReservationServiceApplication.class, args);
	}

}
