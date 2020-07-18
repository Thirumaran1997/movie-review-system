package com.movie.review.ReviewSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.movie.review.ReviewSystem")
public class ReviewSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewSystemApplication.class, args);
	}

}
