package com.example.backend.dto;

import org.springframework.stereotype.Component;

import com.example.backend.entity.Food;

@Component
public class FoodMapper {

    public void updateFoodFromRequest(Food food, FoodRequest request) {
        food.setName(request.getName());
        food.setDescription(request.getDescription());
        food.setCaloriesPer100g(request.getCaloriesPer100g());
        food.setProteinPer100g(request.getProteinPer100g());
        food.setCarbsPer100g(request.getCarbsPer100g());
        food.setFatsPer100g(request.getFatsPer100g());
        food.setFiberPer100g(request.getFiberPer100g());
        food.setSugarPer100g(request.getSugarPer100g());
        food.setSodiumPer100g(request.getSodiumPer100g());
        food.setServingSizeG(request.getServingSizeG());
        food.setServingSizeUnit(request.getServingSizeUnit());
    }
}