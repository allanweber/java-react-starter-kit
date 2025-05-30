package com.example.backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    @NonNull
    Page<Food> findAll(@NonNull Pageable pageable);
}