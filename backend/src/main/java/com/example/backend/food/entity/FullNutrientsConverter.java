package com.example.backend.food.entity;

import java.io.IOException;
import java.util.List;

import com.example.backend.food.client.data.FullNutrient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FullNutrientsConverter implements AttributeConverter<List<FullNutrient>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<FullNutrient> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting FullNutrients to JSON", e);
        }
    }

    @Override
    public List<FullNutrient> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) {
                return null;
            }
            return objectMapper.readValue(dbData, new TypeReference<List<FullNutrient>>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to FullNutrients", e);
        }
    }
}