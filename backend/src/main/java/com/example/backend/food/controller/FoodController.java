package com.example.backend.food.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.food.FoodService;
import com.example.backend.food.client.data.FoodSearchResponse;
import com.example.backend.food.entity.Food;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
@Tag(name = "Foods", description = "Food management APIs")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search foods by name or description")
    public FoodSearchResponse searchFoods(
            @RequestParam String query) {
        return foodService.searchFood(query);
    }

    @GetMapping("/{food}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get food by ID")
    public ResponseEntity<Food> getFoodById(@PathVariable String name) {
        return foodService.getFood(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}