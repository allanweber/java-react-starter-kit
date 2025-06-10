package com.example.backend.food.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.backend.config.TestContainersConfig;
import com.example.backend.food.entity.Food;
import com.example.backend.food.repository.FoodRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(classes = TestContainersConfig.class)
class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void setUp() {
        foodRepository.deleteAll();
    }

    @Test
    void shouldCreateFood() throws Exception {
        // Given
        FoodRequest request = createFoodRequest();

        // When/Then
        mockMvc.perform(post("/api/v1/foods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.caloriesPer100g").value(request.getCaloriesPer100g().doubleValue()))
                .andExpect(jsonPath("$.proteinPer100g").value(request.getProteinPer100g().doubleValue()));

        assertThat(foodRepository.count()).isEqualTo(1);
    }

    @Test
    void shouldGetFoodById() throws Exception {
        // Given
        Food food = createAndSaveFood();

        // When/Then
        mockMvc.perform(get("/api/v1/foods/{id}", food.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(food.getId().toString()))
                .andExpect(jsonPath("$.name").value(food.getName()))
                .andExpect(jsonPath("$.caloriesPer100g").value(food.getCaloriesPer100g().doubleValue()));
    }

    @Test
    void shouldUpdateFood() throws Exception {
        // Given
        Food food = createAndSaveFood();
        FoodRequest updateRequest = createFoodRequest();
        updateRequest.setName("Updated Apple");

        // When/Then
        mockMvc.perform(put("/api/v1/foods/{id}", food.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updateRequest.getName()));

        Food updatedFood = foodRepository.findById(food.getId()).orElseThrow();
        assertThat(updatedFood.getName()).isEqualTo(updateRequest.getName());
    }

    @Test
    void shouldDeleteFood() throws Exception {
        // Given
        Food food = createAndSaveFood();

        // When/Then
        mockMvc.perform(delete("/api/v1/foods/{id}", food.getId()))
                .andExpect(status().isOk());

        assertThat(foodRepository.existsById(food.getId())).isFalse();
    }

    @Test
    void shouldGetAllFoods() throws Exception {
        // Given
        createAndSaveFood();
        createAndSaveFood();

        // When/Then
        mockMvc.perform(get("/api/v1/foods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldSearchFoods() throws Exception {
        // Given
        createAndSaveFood();
        Food banana = createAndSaveFood();
        banana.setName("Banana");
        banana.setDescription("Ripe banana");
        foodRepository.save(banana);

        // When/Then
        mockMvc.perform(get("/api/v1/foods/search")
                .param("query", "apple")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "name")
                .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Apple"));
    }

    @Test
    void shouldSearchFoodsWithPagination() throws Exception {
        // Given
        createAndSaveFood();
        Food banana = createAndSaveFood();
        banana.setName("Banana");
        banana.setDescription("Ripe banana");
        foodRepository.save(banana);
        Food orange = createAndSaveFood();
        orange.setName("Orange");
        orange.setDescription("Fresh orange");
        foodRepository.save(orange);

        // When/Then
        mockMvc.perform(get("/api/v1/foods/search")
                .param("query", "")
                .param("page", "0")
                .param("size", "2")
                .param("sortBy", "name")
                .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.totalElements").value(3))
                .andExpect(jsonPath("$.totalPages").value(2));
    }

    @Test
    void shouldSearchFoodsWithSorting() throws Exception {
        // Given
        createAndSaveFood();
        Food banana = createAndSaveFood();
        banana.setName("Banana");
        banana.setDescription("Ripe banana");
        foodRepository.save(banana);
        Food orange = createAndSaveFood();
        orange.setName("Orange");
        orange.setDescription("Fresh orange");
        foodRepository.save(orange);

        // When/Then
        mockMvc.perform(get("/api/v1/foods/search")
                .param("query", "")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "name")
                .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("$.content[0].name").value("Orange"))
                .andExpect(jsonPath("$.content[1].name").value("Banana"))
                .andExpect(jsonPath("$.content[2].name").value("Apple"));
    }

    @Test
    void shouldReturnEmptyPageWhenNoMatches() throws Exception {
        // Given
        createAndSaveFood();
        Food banana = createAndSaveFood();
        banana.setName("Banana");
        banana.setDescription("Ripe banana");
        foodRepository.save(banana);

        // When/Then
        mockMvc.perform(get("/api/v1/foods/search")
                .param("query", "orange")
                .param("page", "0")
                .param("size", "10")
                .param("sortBy", "name")
                .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(0))
                .andExpect(jsonPath("$.totalElements").value(0))
                .andExpect(jsonPath("$.totalPages").value(0));
    }

    private FoodRequest createFoodRequest() {
        FoodRequest request = new FoodRequest();
        request.setName("Apple");
        request.setDescription("Fresh red apple");
        request.setCaloriesPer100g(new BigDecimal("52"));
        request.setProteinPer100g(new BigDecimal("0.3"));
        request.setCarbsPer100g(new BigDecimal("14"));
        request.setFatsPer100g(new BigDecimal("0.2"));
        request.setFiberPer100g(new BigDecimal("2.4"));
        request.setSugarPer100g(new BigDecimal("10"));
        request.setSodiumPer100g(new BigDecimal("1"));
        request.setServingSizeG(new BigDecimal("100"));
        request.setServingSizeUnit("g");
        return request;
    }

    private Food createAndSaveFood() {
        Food food = new Food();
        food.setName("Apple");
        food.setDescription("Fresh red apple");
        food.setCaloriesPer100g(new BigDecimal("52"));
        food.setProteinPer100g(new BigDecimal("0.3"));
        food.setCarbsPer100g(new BigDecimal("14"));
        food.setFatsPer100g(new BigDecimal("0.2"));
        food.setFiberPer100g(new BigDecimal("2.4"));
        food.setSugarPer100g(new BigDecimal("10"));
        food.setSodiumPer100g(new BigDecimal("1"));
        food.setServingSizeG(new BigDecimal("100"));
        food.setServingSizeUnit("g");
        return foodRepository.save(food);
    }
}