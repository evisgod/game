package com.challenge.application.gameofthree.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class provides swagger configuration for the API documentation
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("game-of-three").select()
				.apis(RequestHandlerSelectors.basePackage("com.challenge.application.gameofthree.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("REST API for playing Game of Three",
				"Application to provide the REST URLs for creating game, player and moves", "Version 1.0",
				"Terms of service", new Contact("Vishnu", "vishnugodara2405@gmail.com", ""), "", "", Collections.emptyList());
	}
}
