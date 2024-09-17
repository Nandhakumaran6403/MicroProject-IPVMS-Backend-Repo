package com.visitor.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ItParkVisitorManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItParkVisitorManagementSystemApplication.class, args);
	}
}
