package com.api.dopaminedb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DopamineDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DopamineDbApplication.class, args);
	}

}
