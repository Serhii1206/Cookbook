package com.example.cookbook.service;

import com.example.cookbook.model.Recipe;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Recipe findById(Long id);

    void deleteById(Long id);

    List<Recipe> findVersionsOfRecipe(PageRequest pageRequest, Long id);
}
