package com.moviewreview.springboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.moviewreview")
public class MovieReviewApp {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewApp.class, args);
	}
}

