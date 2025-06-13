package com.example.backend.food;

import org.springframework.stereotype.Service;

import com.example.backend.food.client.FoodClient;
import com.example.backend.food.client.FoodClientResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodClient foodClient;

    public FoodClientResponse getFood(String foodName) {
        FoodClientResponse foodClientResponse = foodClient.getFood(foodName);

        return foodClientResponse;
    }
}
