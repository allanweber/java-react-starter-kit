package com.example.backend.controller;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FoodRequest {
    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;

    @NotNull
    @Positive
    private BigDecimal caloriesPer100g;

    @NotNull
    @Positive
    private BigDecimal proteinPer100g;

    @NotNull
    @Positive
    private BigDecimal carbsPer100g;

    @NotNull
    @Positive
    private BigDecimal fatsPer100g;

    @NotNull
    @Positive
    private BigDecimal fiberPer100g;

    @NotNull
    @Positive
    private BigDecimal sugarPer100g;

    @NotNull
    @Positive
    private BigDecimal sodiumPer100g;

    @NotNull
    @Positive
    private BigDecimal servingSizeG;

    @Size(max = 50)
    private String servingSizeUnit;
}