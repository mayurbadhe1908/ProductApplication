package com.product.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Product Service REST API documentation",
				description = "Product serviceREST API",
				version = "v1",
				contact = @Contact(
						name = "Mayur Badhe",
						email = "badhemayur@gmail.com"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "sharepoint URL Product Service API",
				url = "example.com"
				)
		)
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
