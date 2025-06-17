package com.example.backend.food.client.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FoodInstantResponse {

    @JsonProperty("nf_calories")
    private Double nfCalories;

    private Photo photo;

    @JsonProperty("food_name")
    private String foodName;

    @JsonProperty("tag_id")
    private String tagId;

    @JsonProperty("nix_item_id")
    private String nixItemId;
}