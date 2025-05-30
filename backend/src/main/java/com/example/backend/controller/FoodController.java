package com.example.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.FoodMapper;
import com.example.backend.dto.FoodRequest;
import com.example.backend.entity.Food;
import com.example.backend.repository.FoodRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
@Tag(name = "Foods", description = "Food management APIs")
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all foods")
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all foods with pagination")
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get food by ID")
    public ResponseEntity<Food> getFoodById(@PathVariable UUID id) {
        return foodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new food")
    public Food createFood(@Valid @RequestBody FoodRequest request) {
        Food food = new Food();
        foodMapper.updateFoodFromRequest(food, request);
        return foodRepository.save(food);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an existing food")
    public ResponseEntity<Food> updateFood(@PathVariable UUID id, @Valid @RequestBody FoodRequest request) {
        return foodRepository.findById(id)
                .map(food -> {
                    foodMapper.updateFoodFromRequest(food, request);
                    return ResponseEntity.ok(foodRepository.save(food));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a food")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID id) {
        return foodRepository.findById(id)
                .map(food -> {
                    foodRepository.delete(food);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}