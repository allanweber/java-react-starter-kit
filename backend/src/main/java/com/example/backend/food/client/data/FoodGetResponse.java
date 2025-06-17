package com.example.backend.food.client.data;

import java.util.List;

import lombok.Data;

@Data
public class FoodGetResponse {
    private List<FoodData> foods;
}