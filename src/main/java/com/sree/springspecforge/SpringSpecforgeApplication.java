package com.sree.springspecforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main Spring Boot application class.
 * Enables JPA Auditing for automatic population of created/updated timestamps.
 */
@SpringBootApplication
@EnableJpaAuditing // Enables Spring Data JPA Auditing features
public class SpringSpecforgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSpecforgeApplication.class, args);
	}

}