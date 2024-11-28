package com.wisescatalog.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wisescatalog.api.service"})
@EnableCaching
public class WisesCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisesCatalogApiApplication.class, args);
	}

}
