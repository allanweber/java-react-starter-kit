package com.example.backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {
    @NonNull
    Page<Food> findAll(@NonNull Pageable pageable);

    @Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(f.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Food> searchByNameOrDescription(@Param("searchTerm") String searchTerm, Pageable pageable);
}