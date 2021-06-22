package com.mortgert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.mortgert"})
public class HackerCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackerCodeApplication.class, args);
	}

}
