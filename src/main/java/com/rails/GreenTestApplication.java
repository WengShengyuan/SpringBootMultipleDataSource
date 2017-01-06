package com.rails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GreenTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenTestApplication.class, args);
	}
}
