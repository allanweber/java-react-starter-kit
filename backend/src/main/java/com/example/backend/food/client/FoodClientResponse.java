package com.example.backend.food.client;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FoodClientResponse {

    private final List<FoodInstantResponse> common;

    private final List<FoodInstantResponse> branded;
}
