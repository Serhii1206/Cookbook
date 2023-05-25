package com.example.cookbook;

import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.RecipeService;
import com.example.cookbook.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void saveRecipe_ValidRecipe_ReturnsSavedRecipe() {
        // Arrange
        Recipe recipe = new Recipe();
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        // Act
        Recipe savedRecipe = recipeService.save(recipe);

        // Assert
        assertNotNull(savedRecipe);
        assertEquals(recipe, savedRecipe);
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    void findById_ExistingId_ReturnsRecipe() {
        // Arrange
        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        // Act
        Recipe foundRecipe = recipeService.findById(recipeId);

        // Assert
        assertNotNull(foundRecipe);
        assertEquals(recipe, foundRecipe);
        verify(recipeRepository, times(1)).findById(recipeId);
    }

    @Test
    void findById_NonExistingId_ThrowsNoSuchElementException() {
        // Arrange
        Long recipeId = 1L;
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> recipeService.findById(recipeId));
        verify(recipeRepository, times(1)).findById(recipeId);
    }

    @Test
    void deleteById_ExistingId_DeletesRecipe() {
        // Arrange
        Long recipeId = 1L;

        // Act
        recipeService.deleteById(recipeId);

        // Assert
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }

//    @Test
//    void findVersionsOfRecipe_ValidId_ReturnsListOfRecipes() {
//        // Arrange
//        Long recipeId = 1L;
//        Recipe recipe = new Recipe();
//        recipe.setId(recipeId);
//
//        Recipe parentRecipe1 = new Recipe();
//        parentRecipe1.setId(2L);
//        parentRecipe1.setParentRecipe(recipe);
//
//        Recipe parentRecipe2 = new Recipe();
//        parentRecipe2.setId(3L);
//        parentRecipe2.setParentRecipe(parentRecipe1);
//
//        List<Recipe> recipes = new ArrayList<>();
//        recipes.add(recipe);
//        recipes.add(parentRecipe1);
//        recipes.add(parentRecipe2);
//
//        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
//
//        // Act
//        List<Recipe> foundRecipes = recipeService.findVersionsOfRecipe(PageRequest.of(0, 10), recipeId);
//
//        // Assert
//        assertNotNull(foundRecipes);
//        assertEquals(recipes.size(), foundRecipes.size());
//        assertTrue(foundRecipes.contains(recipe));
//        assertTrue(foundRecipes.contains(parentRecipe1));
//        assertTrue(foundRecipes.contains(parentRecipe2));
//        verify(recipeRepository, times(1)).findById(recipeId);
//    }
}
