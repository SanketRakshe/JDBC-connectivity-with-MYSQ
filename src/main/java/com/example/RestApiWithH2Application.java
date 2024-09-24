package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiWithH2Application {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithH2Application.class, args);
		System.out.println("Started..!");
	}

}
