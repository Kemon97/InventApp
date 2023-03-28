package com.uco.inventapp;

import org.sonarsource.sonarlint.shaded.org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class InventappApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventappApplication.class, args);
	}

}
