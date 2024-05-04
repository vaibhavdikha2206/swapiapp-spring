package com.example.sapientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SapientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapientappApplication.class, args);
	}

}
