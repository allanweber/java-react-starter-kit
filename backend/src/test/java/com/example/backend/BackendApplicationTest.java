package com.example.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.backend.config.TestContainersConfig;
import com.example.backend.food.client.FoodConfigurationProperties;

@Import(TestContainersConfig.class)
@EnableConfigurationProperties(FoodConfigurationProperties.class)
@SpringBootTest
public class BackendApplicationTest {
    @Test
    @DisplayName("Given Spring Boot application, when starting application, then should load context successfully")
    void testMain() {

    }
}
