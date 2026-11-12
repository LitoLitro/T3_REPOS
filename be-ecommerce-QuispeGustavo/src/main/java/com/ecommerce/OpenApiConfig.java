package com.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API REST Ecommerce")
                        .description("Documentación de la API REST del sistema Ecommerce")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Gustavo Quispe")
                                .email("gustavo@example.com")));
    }
}