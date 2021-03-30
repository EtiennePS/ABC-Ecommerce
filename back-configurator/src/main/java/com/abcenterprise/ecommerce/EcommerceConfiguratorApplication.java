package com.abcenterprise.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.abcenterprise.ecommerce.core.EcommerceCoreApplication;

@ComponentScan(basePackages = {"com.abcenterprise.ecommerce"})
@EntityScan(basePackages = {"com.abcenterprise.ecommerce"})
@EnableJpaRepositories(basePackages = {"com.abcenterprise.ecommerce"})
@Import(EcommerceCoreApplication.class)
@SpringBootApplication
public class EcommerceConfiguratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceConfiguratorApplication.class, args);
	}

}
