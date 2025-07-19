package com.alirizakaygusuz.starter;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import io.github.cdimascio.dotenv.Dotenv;


public class DotenvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("JWT_SECRET_KEY", dotenv.get("JWT_SECRET_KEY"));
        System.setProperty("ACCESS_TOKEN_EXPIRE_SECONDS", dotenv.get("ACCESS_TOKEN_EXPIRE_SECONDS"));
        System.setProperty("REFRESH_TOKEN_EXPIRE_SECONDS", dotenv.get("REFRESH_TOKEN_EXPIRE_SECONDS"));
        System.setProperty("API_KEY", dotenv.get("API_KEY"));

        System.out.println("[.env] Environment variables loaded before Spring Boot context starts.");
    }
    
}