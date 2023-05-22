package com.example.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cookbook.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
