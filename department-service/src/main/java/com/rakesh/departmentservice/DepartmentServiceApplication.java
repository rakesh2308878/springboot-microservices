package com.rakesh.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Department Service Application",
				summary = "This application used to maintain department related information",
				description = "Department service Rest API",
				version = "v1.0",
				termsOfService = "@2024",
				contact = @Contact(
						name = "Rakesh Kumar",
						email = "rakesh.kumar@gmail.com",
						url = "rakesh.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "rakesh.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				url = "exteraldoc.com",
				description = "This is external doc"
		)
)

@SpringBootApplication
@EnableDiscoveryClient
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
