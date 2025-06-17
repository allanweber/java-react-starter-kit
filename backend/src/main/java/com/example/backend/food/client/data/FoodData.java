package com.example.backend.food.client.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FoodData {
    @JsonProperty("food_name")
    private String foodName;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("serving_qty")
    private Double servingQuantity;

    @JsonProperty("serving_unit")
    private String servingUnit;

    @JsonProperty("serving_weight_grams")
    private Double servingWeightGrams;

    @JsonProperty("nf_calories")
    private Double calories;

    @JsonProperty("nf_total_fat")
    private Double totalFat;

    @JsonProperty("nf_saturated_fat")
    private Double saturatedFat;

    @JsonProperty("nf_cholesterol")
    private Double cholesterol;

    @JsonProperty("nf_sodium")
    private Double sodium;

    @JsonProperty("nf_total_carbohydrate")
    private Double totalCarbohydrate;

    @JsonProperty("nf_dietary_fiber")
    private Double dietaryFiber;

    @JsonProperty("nf_sugars")
    private Double sugars;

    @JsonProperty("nf_protein")
    private Double protein;

    @JsonProperty("nf_potassium")
    private Double potassium;

    @JsonProperty("nf_p")
    private Double phosphorus;

    @JsonProperty("full_nutrients")
    private List<FullNutrient> fullNutrients;

    private Metadata metadata;

    @JsonProperty("alt_measures")
    private List<AltMeasure> altMeasures;

    private Photo photo;
}