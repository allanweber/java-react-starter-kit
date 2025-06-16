package com.example.backend.food;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.food.client.FoodClient;
import com.example.backend.food.client.FoodClientResponse;
import com.example.backend.food.entity.Food;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodClient foodClient;

    public FoodClientResponse searchFood(String foodName) {
        return foodClient.getFood(foodName);
    }

    public Optional<Food> getFood(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getFood'");
    }
}
