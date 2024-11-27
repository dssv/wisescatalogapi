package com.wisescatalog.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WisesCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisesCatalogApiApplication.class, args);
	}

}
