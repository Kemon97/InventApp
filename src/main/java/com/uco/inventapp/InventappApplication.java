package com.uco.inventapp;

import org.sonarsource.sonarlint.shaded.org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories("com.uco.inventapp.repository")
@ComponentScan(basePackages = { "com.uco.inventapp", "com.uco.config" })
@EntityScan("com.uco.inventapp.domain")
@CrossOrigin("*")
@EnableWebMvc
@SpringBootApplication
public class InventappApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventappApplication.class, args);
	}

	public class CorsConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("*") // Especifica el origen permitido
					.allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos HTTP permitidos
					.allowedHeaders("*"); // Especifica los encabezados permitidos
		}
	}
}
