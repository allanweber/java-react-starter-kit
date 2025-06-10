package com.example.backend.food.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.backend.config.TestContainersConfig;
import com.example.backend.food.entity.Food;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ContextConfiguration(classes = TestContainersConfig.class)
class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void setUp() {
        foodRepository.deleteAll();
    }

    @Test
    void shouldSearchFoodsByName() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        foodRepository.saveAll(List.of(apple, banana));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("apple", PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Apple");
    }

    @Test
    void shouldSearchFoodsByDescription() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        foodRepository.saveAll(List.of(apple, banana));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("ripe", PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Banana");
    }

    @Test
    void shouldSearchFoodsCaseInsensitive() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        foodRepository.saveAll(List.of(apple, banana));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("APPLE", PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Apple");
    }

    @Test
    void shouldReturnEmptyPageWhenNoMatches() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        foodRepository.saveAll(List.of(apple, banana));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("orange", PageRequest.of(0, 10));

        // Then
        assertThat(result.getContent()).isEmpty();
    }

    @Test
    void shouldRespectPagination() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        Food orange = createFood("Orange", "Fresh orange");
        foodRepository.saveAll(List.of(apple, banana, orange));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("", PageRequest.of(0, 2));

        // Then
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getTotalPages()).isEqualTo(2);
    }

    @Test
    void shouldRespectSorting() {
        // Given
        Food apple = createFood("Apple", "Fresh red apple");
        Food banana = createFood("Banana", "Ripe banana");
        Food orange = createFood("Orange", "Fresh orange");
        foodRepository.saveAll(List.of(apple, banana, orange));

        // When
        Page<Food> result = foodRepository.searchByNameOrDescription("",
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name")));

        // Then
        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Orange");
        assertThat(result.getContent().get(1).getName()).isEqualTo("Banana");
        assertThat(result.getContent().get(2).getName()).isEqualTo("Apple");
    }

    private Food createFood(String name, String description) {
        Food food = new Food();
        food.setName(name);
        food.setDescription(description);
        food.setCaloriesPer100g(new BigDecimal("52"));
        food.setProteinPer100g(new BigDecimal("0.3"));
        food.setCarbsPer100g(new BigDecimal("14"));
        food.setFatsPer100g(new BigDecimal("0.2"));
        food.setFiberPer100g(new BigDecimal("2.4"));
        food.setSugarPer100g(new BigDecimal("10"));
        food.setSodiumPer100g(new BigDecimal("1"));
        food.setServingSizeG(new BigDecimal("100"));
        food.setServingSizeUnit("g");
        food.setImageUrl("https://example.com/image.jpg");
        return food;
    }
}