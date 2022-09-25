package com.example.hsbcassignmentweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.hsbcassignmentweb"})
public class HsbcassignmentwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsbcassignmentwebApplication.class, args);
	}

}
