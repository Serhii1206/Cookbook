package com.example.cookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.cookbook.repository")
public class CookbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookbookApplication.class, args);
	}

}
