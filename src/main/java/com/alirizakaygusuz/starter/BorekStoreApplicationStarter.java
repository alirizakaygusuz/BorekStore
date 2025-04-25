package com.alirizakaygusuz.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.alirizakaygusuz"})
@EntityScan(basePackages = {"com.alirizakaygusuz"})
@EnableJpaRepositories(basePackages = {"com.alirizakaygusuz"})


@SpringBootApplication
public class BorekStoreApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(BorekStoreApplicationStarter.class, args);
	}

}
