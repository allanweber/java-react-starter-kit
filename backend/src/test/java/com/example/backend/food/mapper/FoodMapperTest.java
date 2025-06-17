package com.example.backend.food.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.client.data.Photo;
import com.example.backend.food.entity.Food;

class FoodMapperTest {

    private final FoodMapper mapper = Mappers.getMapper(FoodMapper.class);

    @Test
    @DisplayName("Given food data with basic fields, when mapping to entity, then should map all basic fields correctly")
    void shouldMapBasicFields() {
        FoodData foodData = new FoodData();
        foodData.setFoodName("Apple");
        foodData.setBrandName("Organic");
        foodData.setServingUnit("piece");
        foodData.setServingWeightGrams(150.0);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getName()).isEqualTo("Apple");
        assertThat(food.getDescription()).isEqualTo("Organic");
        assertThat(food.getServingSizeUnit()).isEqualTo("piece");
        assertThat(food.getServingSizeG()).isEqualByComparingTo(BigDecimal.valueOf(150.0));
    }

    @Test
    @DisplayName("Given food data with nutritional values, when mapping to entity, then should convert values to per 100g")
    void shouldMapNutritionalValuesToPer100g() {
        FoodData foodData = new FoodData();
        foodData.setCalories(75.0);
        foodData.setProtein(0.5);
        foodData.setTotalCarbohydrate(19.0);
        foodData.setTotalFat(0.3);
        foodData.setDietaryFiber(2.4);
        foodData.setSugars(14.0);
        foodData.setSodium(1.0);
        foodData.setServingWeightGrams(150.0);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getCaloriesPer100g()).isEqualByComparingTo(BigDecimal.valueOf(50.0));
        assertThat(food.getProteinPer100g()).isEqualByComparingTo(BigDecimal.valueOf(0.33));
        assertThat(food.getCarbsPer100g()).isEqualByComparingTo(BigDecimal.valueOf(12.67));
        assertThat(food.getFatsPer100g()).isEqualByComparingTo(BigDecimal.valueOf(0.2));
        assertThat(food.getFiberPer100g()).isEqualByComparingTo(BigDecimal.valueOf(1.6));
        assertThat(food.getSugarPer100g()).isEqualByComparingTo(BigDecimal.valueOf(9.33));
        assertThat(food.getSodiumPer100g()).isEqualByComparingTo(BigDecimal.valueOf(0.67));
    }

    @Test
    @DisplayName("Given food data with null values, when mapping to entity, then should handle nulls gracefully")
    void shouldHandleNullValues() {
        FoodData foodData = new FoodData();
        foodData.setFoodName("Apple");
        foodData.setServingWeightGrams(null);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getName()).isEqualTo("Apple");
        assertThat(food.getCaloriesPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getProteinPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getCarbsPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getFatsPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getFiberPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getSugarPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getSodiumPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Given food data with photo, when mapping to entity, then should map image URL correctly")
    void shouldMapImageUrl() {
        FoodData foodData = new FoodData();
        Photo photo = new Photo();
        photo.setHighres("https://example.com/apple.jpg");
        foodData.setPhoto(photo);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getImageUrl()).isEqualTo("https://example.com/apple.jpg");
    }

    @Test
    @DisplayName("Given food data with zero serving weight, when mapping to entity, then should handle zero values correctly")
    void shouldHandleZeroServingWeight() {
        FoodData foodData = new FoodData();
        foodData.setCalories(75.0);
        foodData.setServingWeightGrams(0.0);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getCaloriesPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Given complete food data with all fields, when mapping to entity, then should map all fields correctly")
    void shouldMapComplexFoodData() {
        FoodData foodData = new FoodData();
        foodData.setFoodName("Chicken Breast");
        foodData.setBrandName("Organic Farms");
        foodData.setServingUnit("piece");
        foodData.setServingWeightGrams(170.0);
        foodData.setCalories(284.0);
        foodData.setProtein(53.0);
        foodData.setTotalCarbohydrate(0.0);
        foodData.setTotalFat(6.2);
        foodData.setDietaryFiber(0.0);
        foodData.setSugars(0.0);
        foodData.setSodium(74.0);

        Photo photo = new Photo();
        photo.setHighres("https://example.com/chicken.jpg");
        foodData.setPhoto(photo);

        Food food = mapper.toEntity(foodData);

        assertThat(food.getName()).isEqualTo("Chicken Breast");
        assertThat(food.getDescription()).isEqualTo("Organic Farms");
        assertThat(food.getServingSizeUnit()).isEqualTo("piece");
        assertThat(food.getServingSizeG()).isEqualByComparingTo(BigDecimal.valueOf(170.0));
        assertThat(food.getCaloriesPer100g()).isEqualByComparingTo(BigDecimal.valueOf(167.06));
        assertThat(food.getProteinPer100g()).isEqualByComparingTo(BigDecimal.valueOf(31.18));
        assertThat(food.getCarbsPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getFatsPer100g()).isEqualByComparingTo(BigDecimal.valueOf(3.65));
        assertThat(food.getFiberPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getSugarPer100g()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(food.getSodiumPer100g()).isEqualByComparingTo(BigDecimal.valueOf(43.53));
        assertThat(food.getImageUrl()).isEqualTo("https://example.com/chicken.jpg");
    }
}