package com.example.backend.food;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.example.backend.food.client.FoodClient;
import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.entity.Food;
import com.example.backend.food.mapper.FoodMapper;
import com.example.backend.food.repository.FoodRepository;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodClient foodClient;

    @Mock
    private FoodMapper foodMapper;

    @Mock
    private FoodRepository foodRepository;

    private FoodService foodService;

    @BeforeEach
    void setUp() {
        foodService = new FoodService(foodClient, foodMapper, foodRepository);
    }

    @Test
    @DisplayName("Given a food name that exists in database, when getting food, then should return the existing food")
    void getFood_WhenFoodExistsInDatabase_ShouldReturnExistingFood() {
        String foodName = "Apple";
        Food existingFood = new Food();
        existingFood.setName(foodName);
        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.of(existingFood));

        Optional<Food> result = foodService.getFood(foodName);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(foodName);
        verifyNoMoreInteractions(foodClient, foodMapper);
    }

    @Test
    @DisplayName("Given a food name not in database but found in API, when getting food, then should save and return the food")
    void getFood_WhenFoodNotInDatabaseButFoundInApi_ShouldSaveAndReturnFood() {
        String foodName = "Banana";
        FoodData foodData = new FoodData();
        foodData.setFoodName(foodName);
        Food mappedFood = new Food();
        mappedFood.setName(foodName);

        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.empty());
        when(foodClient.getFood(foodName)).thenReturn(Optional.of(foodData));
        when(foodMapper.toEntity(foodData)).thenReturn(mappedFood);
        when(foodRepository.save(any(Food.class))).thenReturn(mappedFood);

        Optional<Food> result = foodService.getFood(foodName);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(foodName);
    }

    @Test
    @DisplayName("Given a food name not in database and not found in API, when getting food, then should return empty")
    void getFood_WhenFoodNotInDatabaseAndNotFoundInApi_ShouldReturnEmpty() {
        String foodName = "NonExistentFood";
        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.empty());
        when(foodClient.getFood(foodName)).thenReturn(Optional.empty());

        Optional<Food> result = foodService.getFood(foodName);

        assertThat(result).isEmpty();
        verifyNoMoreInteractions(foodClient, foodMapper);
    }

    @Test
    @DisplayName("Given a null food name, when getting food, then should return empty")
    void getFood_WhenFoodNameIsNull_ShouldReturnEmpty() {
        Optional<Food> result = foodService.getFood(null);
        assertThat(result).isEmpty();
        verify(foodRepository, times(1)).findByNameIgnoreCase(null);
    }

    @Test
    @DisplayName("Given an empty food name, when getting food, then should return empty")
    void getFood_WhenFoodNameIsEmpty_ShouldReturnEmpty() {
        Optional<Food> result = foodService.getFood("");
        assertThat(result).isEmpty();
        verify(foodRepository, times(1)).findByNameIgnoreCase("");
    }

    @Test
    @DisplayName("Given a blank food name, when getting food, then should return empty")
    void getFood_WhenFoodNameIsBlank_ShouldReturnEmpty() {
        Optional<Food> result = foodService.getFood("   ");
        assertThat(result).isEmpty();
        verify(foodRepository, times(1)).findByNameIgnoreCase("   ");
    }

    @Test
    @DisplayName("Given a food name with different case, when getting food, then should return the food case-insensitively")
    void getFood_ShouldBeCaseInsensitive() {
        String foodName = "Apple";
        String differentCase = "APPLE";
        Food existingFood = new Food();
        existingFood.setName(foodName);
        when(foodRepository.findByNameIgnoreCase(differentCase)).thenReturn(Optional.of(existingFood));

        Optional<Food> result = foodService.getFood(differentCase);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(foodName);
    }

    @Test
    @DisplayName("Given a food name that causes API error, when getting food, then should throw exception")
    void getFood_WhenApiThrowsException_ShouldThrowException() {
        String foodName = "ErrorFood";
        RuntimeException apiError = new RuntimeException("API Error");
        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.empty());
        when(foodClient.getFood(foodName)).thenThrow(apiError);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            foodService.getFood(foodName);
        });

        assertThat(thrown).isEqualTo(apiError);
        verify(foodRepository, times(1)).findByNameIgnoreCase(foodName);
        verify(foodClient, times(1)).getFood(foodName);
    }

    @Test
    @DisplayName("Given a food name that causes repository save error, when getting food, then should throw exception")
    void getFood_WhenRepositorySaveThrowsException_ShouldThrowException() {
        String foodName = "SaveErrorFood";
        FoodData foodData = new FoodData();
        foodData.setFoodName(foodName);
        Food mappedFood = new Food();
        mappedFood.setName(foodName);
        DataAccessException saveError = new DataAccessException("Save Error") {
        };

        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.empty());
        when(foodClient.getFood(foodName)).thenReturn(Optional.of(foodData));
        when(foodMapper.toEntity(foodData)).thenReturn(mappedFood);
        when(foodRepository.save(any(Food.class))).thenThrow(saveError);

        DataAccessException thrown = assertThrows(DataAccessException.class, () -> {
            foodService.getFood(foodName);
        });

        assertThat(thrown).isEqualTo(saveError);
        verify(foodRepository, times(1)).findByNameIgnoreCase(foodName);
        verify(foodClient, times(1)).getFood(foodName);
        verify(foodMapper, times(1)).toEntity(foodData);
        verify(foodRepository, times(1)).save(any(Food.class));
    }

    @Test
    @DisplayName("Given a food name that causes mapper error, when getting food, then should throw exception")
    void getFood_WhenMapperThrowsException_ShouldThrowException() {
        String foodName = "MapperErrorFood";
        FoodData foodData = new FoodData();
        foodData.setFoodName(foodName);
        RuntimeException mapperError = new RuntimeException("Mapper Error");

        when(foodRepository.findByNameIgnoreCase(foodName)).thenReturn(Optional.empty());
        when(foodClient.getFood(foodName)).thenReturn(Optional.of(foodData));
        when(foodMapper.toEntity(foodData)).thenThrow(mapperError);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            foodService.getFood(foodName);
        });

        assertThat(thrown).isEqualTo(mapperError);
        verify(foodRepository, times(1)).findByNameIgnoreCase(foodName);
        verify(foodClient, times(1)).getFood(foodName);
        verify(foodMapper, times(1)).toEntity(foodData);
        verify(foodRepository, times(0)).save(any());
    }
}