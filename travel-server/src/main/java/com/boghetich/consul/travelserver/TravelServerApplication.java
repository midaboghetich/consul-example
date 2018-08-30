package com.boghetich.consul.travelserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TravelServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServerApplication.class, args);
	}
}
