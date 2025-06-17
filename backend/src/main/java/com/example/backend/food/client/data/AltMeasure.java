package com.example.backend.food.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AltMeasure {
    @JsonProperty("serving_weight")
    private Double servingWeight;
    private String measure;
    private Double qty;
}