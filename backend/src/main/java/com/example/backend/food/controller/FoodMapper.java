package com.example.backend.food.controller;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.example.backend.food.entity.Food;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {

    void updateFoodFromRequest(@MappingTarget Food food, FoodRequest request);
}