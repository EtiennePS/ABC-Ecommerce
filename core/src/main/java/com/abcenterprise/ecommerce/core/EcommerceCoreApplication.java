package com.abcenterprise.ecommerce.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application-shared.properties")
@SpringBootApplication
public class EcommerceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCoreApplication.class, args);
	}

}
