package com.example.backend.food.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.backend.config.TestContainersConfig;
import com.example.backend.food.client.FoodConfigurationProperties;

@Testcontainers
@ContextConfiguration(classes = TestContainersConfig.class)
@SpringBootTest
@EnableConfigurationProperties(FoodConfigurationProperties.class)
@TestPropertySource(properties = {
        "food.app-id=test-app-id",
        "food.app-key=test-app-key",
        "food.app-url=https://test-api.example.com"
})
public class FoodConfigurationTest {

    @Autowired
    private FoodConfigurationProperties foodConfiguration;

    @Test
    @DisplayName("Given food configuration properties, when loading configuration, then should load all properties correctly")
    void shouldLoadConfigurationProperties() {
        assertThat(foodConfiguration).isNotNull();
        assertThat(foodConfiguration.getAppId()).isEqualTo("test-app-id");
        assertThat(foodConfiguration.getAppKey()).isEqualTo("test-app-key");
        assertThat(foodConfiguration.getAppUrl()).isEqualTo("https://test-api.example.com");
    }
}
