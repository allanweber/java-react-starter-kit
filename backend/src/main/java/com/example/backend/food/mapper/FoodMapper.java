package com.example.backend.food.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.entity.Food;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    int SCALE = 2;
    RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "foodName")
    @Mapping(target = "description", source = "brandName")
    @Mapping(target = "caloriesPer100g", expression = "java(convertToPer100g(foodData.getCalories(), foodData.getServingWeightGrams()))")
    @Mapping(target = "proteinPer100g", expression = "java(convertToPer100g(foodData.getProtein(), foodData.getServingWeightGrams()))")
    @Mapping(target = "carbsPer100g", expression = "java(convertToPer100g(foodData.getTotalCarbohydrate(), foodData.getServingWeightGrams()))")
    @Mapping(target = "fatsPer100g", expression = "java(convertToPer100g(foodData.getTotalFat(), foodData.getServingWeightGrams()))")
    @Mapping(target = "fiberPer100g", expression = "java(convertToPer100g(foodData.getDietaryFiber(), foodData.getServingWeightGrams()))")
    @Mapping(target = "sugarPer100g", expression = "java(convertToPer100g(foodData.getSugars(), foodData.getServingWeightGrams()))")
    @Mapping(target = "sodiumPer100g", expression = "java(convertToPer100g(foodData.getSodium(), foodData.getServingWeightGrams()))")
    @Mapping(target = "servingSizeG", source = "servingWeightGrams")
    @Mapping(target = "servingSizeUnit", source = "servingUnit")
    @Mapping(target = "imageUrl", source = "photo.highres")
    @Mapping(target = "fullNutrients", source = "fullNutrients")
    @Mapping(target = "altMeasures", source = "altMeasures")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Food toEntity(FoodData foodData);

    default BigDecimal convertToPer100g(Double value, Double servingWeight) {
        if (value == null || servingWeight == null || servingWeight <= 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf((value * 100) / servingWeight)
                .setScale(SCALE, ROUNDING_MODE);
    }
}