package com.work.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class RestWorkmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWorkmanagementApplication.class, args);
	}

}
