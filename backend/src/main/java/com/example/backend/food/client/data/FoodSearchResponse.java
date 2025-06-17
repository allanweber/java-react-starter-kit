package com.example.backend.food.client.data;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FoodSearchResponse {

    private final List<FoodInstantResponse> common;

    private final List<FoodInstantResponse> branded;
}
