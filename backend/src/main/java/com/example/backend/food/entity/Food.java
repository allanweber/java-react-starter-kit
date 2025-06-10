package com.example.backend.food.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @PositiveOrZero
    @Column(name = "calories_per_100g", precision = 10, scale = 2)
    private BigDecimal caloriesPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "protein_per_100g", precision = 10, scale = 2)
    private BigDecimal proteinPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "carbs_per_100g", precision = 10, scale = 2)
    private BigDecimal carbsPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "fats_per_100g", precision = 10, scale = 2)
    private BigDecimal fatsPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "fiber_per_100g", precision = 10, scale = 2)
    private BigDecimal fiberPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "sugar_per_100g", precision = 10, scale = 2)
    private BigDecimal sugarPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "sodium_per_100g", precision = 10, scale = 2)
    private BigDecimal sodiumPer100g;

    @NotNull
    @PositiveOrZero
    @Column(name = "serving_size_g", precision = 10, scale = 2)
    private BigDecimal servingSizeG;

    @Size(max = 50)
    @Column(name = "serving_size_unit", length = 50)
    private String servingSizeUnit;

    @Size(max = 255)
    @Column(name = "image_url")
    @JsonProperty("image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}