package com.example.backend.food.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.backend.config.ObjectMapperInstance;
import com.example.backend.food.client.data.FoodData;
import com.example.backend.food.client.data.FoodGetResponse;
import com.example.backend.food.client.data.FoodInstantResponse;
import com.example.backend.food.client.data.FoodSearchResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NutrimixClient implements FoodClient {

    private final RestClient foodRestClient;
    private final ObjectMapper objectMapper = ObjectMapperInstance.getInstance();
    private static final TypeReference<List<FoodInstantResponse>> toValueTypeRef = new TypeReference<>() {
    };

    private static final int SEARCH_CACHE_SIZE = 1000;
    private static final int FOOD_DATA_CACHE_SIZE = 500;

    private final Map<String, FoodSearchResponse> searchCache = Collections.synchronizedMap(
            new LinkedHashMap<String, FoodSearchResponse>(SEARCH_CACHE_SIZE, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, FoodSearchResponse> eldest) {
                    return size() > SEARCH_CACHE_SIZE;
                }
            });

    private final Map<String, FoodData> foodDataCache = Collections.synchronizedMap(
            new LinkedHashMap<String, FoodData>(FOOD_DATA_CACHE_SIZE, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, FoodData> eldest) {
                    return size() > FOOD_DATA_CACHE_SIZE;
                }
            });

    @Override
    public FoodSearchResponse search(String foodName) {
        FoodSearchResponse cachedResponse = searchCache.get(foodName);
        if (cachedResponse != null) {
            System.out.println("Cached response found for " + foodName);
            return cachedResponse;
        }

        Map<String, Object> body = foodRestClient.get()
                .uri("v2/search/instant/?query={foodName}", foodName)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                });

        @SuppressWarnings("null")
        List<FoodInstantResponse> common = objectMapper.convertValue(body.get("common"), toValueTypeRef);
        List<FoodInstantResponse> branded = objectMapper.convertValue(body.get("branded"), toValueTypeRef);

        common = common.stream()
                .collect(Collectors.groupingBy(FoodInstantResponse::getTagId))
                .values()
                .stream()
                .map(list -> list.get(0))
                .collect(Collectors.toList());

        branded = branded.stream()
                .collect(Collectors.groupingBy(FoodInstantResponse::getNixItemId))
                .values()
                .stream()
                .map(list -> list.get(0))
                .collect(Collectors.toList());

        FoodSearchResponse response = new FoodSearchResponse(common, branded);

        System.out.println("Adding response to cache for " + foodName);
        searchCache.put(foodName, response);

        return response;
    }

    @Override
    public Optional<FoodData> getFood(String foodName) {
        FoodData cachedData = foodDataCache.get(foodName);
        if (cachedData != null) {
            System.out.println("Cached food data found for " + foodName);
            return Optional.of(cachedData);
        }

        Map<String, String> requestBody = Map.of("query", foodName);

        FoodGetResponse response = foodRestClient.post()
                .uri("v2/natural/nutrients")
                .body(requestBody)
                .retrieve()
                .body(FoodGetResponse.class);

        if (response == null || response.getFoods() == null || response.getFoods().isEmpty()) {
            return Optional.empty();
        }

        FoodData foodData = response.getFoods().get(0);

        System.out.println("Adding food data to cache for " + foodName);
        foodDataCache.put(foodName, foodData);

        return Optional.of(foodData);
    }
}
