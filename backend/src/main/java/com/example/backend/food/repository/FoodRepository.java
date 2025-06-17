package com.example.backend.food.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.example.backend.food.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @NonNull
    Page<Food> findAll(@NonNull Pageable pageable);

    Optional<Food> findByNameIgnoreCase(String name);
}