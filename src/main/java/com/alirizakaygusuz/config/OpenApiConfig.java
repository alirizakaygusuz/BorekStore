package com.alirizakaygusuz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI springBorekOpenAPI() {
	    String securitySchemeName="bearerAuth";
		return new OpenAPI()
	    		.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
	            .components(new Components()
	                .addSecuritySchemes(securitySchemeName,
	                    new SecurityScheme()
	                        .name(securitySchemeName)
	                        .type(SecurityScheme.Type.HTTP)
	                        .scheme("bearer")
	                        .bearerFormat("JWT")
	                        .description("Use the format 'Bearer {token}' for authentication. Example: Bearer eyJhbGciOi... " +
	                                "You can obtain a token from the /auth/authenticate endpoint and refresh it via /auth/refresh-token.")

	                )
	            )
	        .info(new Info()
	            .title("Borek Market Backend API")
	            .description("Basic Pastry Store API - Swagger Test Area")
	            .version("v1.0.0")
	            .contact(new Contact().name("Ali Rıza Kaygusuz").email("email@domain.com"))
	            .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
	        )
	        .externalDocs(new ExternalDocumentation()
	            .description("GitHub Repo")
	            .url("https://github.com/alirizakaygusuz/borekstore"))
	        .addServersItem(new Server().url("http://localhost:8080").description("Local Dev Server"));
	}

}
