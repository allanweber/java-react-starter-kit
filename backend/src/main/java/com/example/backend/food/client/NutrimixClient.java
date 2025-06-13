package com.example.backend.food.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.backend.config.ObjectMapperInstance;
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

    private static final int CACHE_SIZE = 1000;
    private final Map<String, FoodClientResponse> cache = Collections.synchronizedMap(
            new LinkedHashMap<String, FoodClientResponse>(CACHE_SIZE, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, FoodClientResponse> eldest) {
                    return size() > CACHE_SIZE;
                }
            });

    @Override
    public FoodClientResponse getFood(String foodName) {
        FoodClientResponse cachedResponse = cache.get(foodName);
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

        FoodClientResponse response = new FoodClientResponse(common, branded);

        System.out.println("Adding response to cache for " + foodName);
        cache.put(foodName, response);

        return response;
    }
}
