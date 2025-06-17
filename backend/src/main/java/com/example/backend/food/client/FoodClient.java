package com.example.backend.food.client;

import java.util.Optional;

import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.client.data.FoodSearchResponse;

public interface FoodClient {

    FoodSearchResponse search(String foodName);

    Optional<FoodData> getFood(String foodName);
}
