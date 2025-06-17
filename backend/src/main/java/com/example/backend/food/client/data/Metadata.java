package com.example.backend.food.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Metadata {
    @JsonProperty("is_raw_food")
    private Boolean isRawFood;
}