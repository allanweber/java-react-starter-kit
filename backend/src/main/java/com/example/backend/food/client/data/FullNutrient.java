package com.example.backend.food.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FullNutrient {
    @JsonProperty("attr_id")
    private Integer attrId;
    private Double value;
}