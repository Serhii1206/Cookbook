package com.example.cookbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.RecipeService;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findVersionsOfRecipe(PageRequest pageRequest, Long id) {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find recipe by id: " + id));
        recipes.add(recipe);
        Recipe parentRecipe = recipe.getParentRecipe();
        while (parentRecipe.getParentRecipe() != null) {
            recipes.add(parentRecipe);
            parentRecipe = parentRecipe.getParentRecipe();
        }
        return recipes;
    }
}
