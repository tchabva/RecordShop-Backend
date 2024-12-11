package com.northcoders.recordshopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NorthcodersRecordShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthcodersRecordShopBackendApplication.class, args);
	}

}
