package com.example.clinic.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customApi() {
        GroupedOpenApi groupedOpenApi = GroupedOpenApi.builder()
                .group("custom-api")
                .packagesToScan("com.example.clinic.controller")
                .build();
        System.out.println("Swagger Configuration: " + groupedOpenApi);
        return groupedOpenApi;
    }
}