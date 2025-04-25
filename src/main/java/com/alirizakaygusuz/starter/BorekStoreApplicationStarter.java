package com.alirizakaygusuz.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = {"com.alirizakaygusuz"})

@SpringBootApplication
public class BorekStoreApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(BorekStoreApplicationStarter.class, args);
	}

}
