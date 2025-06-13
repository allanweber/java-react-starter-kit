package com.example.backend.food.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FoodConfiguration {

    @Bean(name = "foodRestClient")
    public RestClient restClient(FoodConfigurationProperties foodConfigurationProperties) {
        return RestClient.builder()
                .baseUrl(foodConfigurationProperties.getAppUrl())
                .defaultHeader("x-app-id", foodConfigurationProperties.getAppId())
                .defaultHeader("x-app-key", foodConfigurationProperties.getAppKey())
                .build();
    }
}
