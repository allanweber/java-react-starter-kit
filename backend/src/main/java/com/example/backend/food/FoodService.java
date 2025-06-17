package com.example.backend.food;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.food.client.FoodClient;
import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.client.data.FoodSearchResponse;
import com.example.backend.food.entity.Food;
import com.example.backend.food.mapper.FoodMapper;
import com.example.backend.food.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodClient foodClient;
    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;

    public FoodSearchResponse searchFood(String foodName) {
        return foodClient.search(foodName);
    }

    @Transactional
    public Optional<Food> getFood(String name) {
        Optional<Food> existingFood = foodRepository.findByNameIgnoreCase(name);
        if (existingFood.isPresent()) {
            return existingFood;
        }

        Optional<FoodData> foodData = foodClient.getFood(name);
        return foodData.map(data -> {
            Food food = foodMapper.toEntity(data);
            return foodRepository.save(food);
        });
    }
}
