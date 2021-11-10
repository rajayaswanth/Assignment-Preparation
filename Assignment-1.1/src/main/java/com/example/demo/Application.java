package com.example.demo;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableRetry
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo")).build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header"); 
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Assignment", 
				"Crud operations using Spring Boot, Jpa, Hibernate, Spring Security, JWT, Swagger, JUnit, Mockito", "0.0.1", 
				"", 
				new Contact("Yaswanth", "", "yaswanthmdh@gmail.com"), 
				"", 
				"", 
				Collections.emptyList());
	}

}
