package com.uco.inventapp.inventapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories("com.uco.inventapp.inventapp.repository")
@ComponentScan(basePackages = { "com.uco.inventapp.inventapp" })
@EntityScan("com.uco.inventapp.inventapp.domain")
@EnableWebMvc
@SpringBootApplication
public class InventappApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventappApplication.class, args);
	}

}
