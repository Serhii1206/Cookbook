package com.example.cookbook;

import com.example.cookbook.model.Cookbook;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.repository.CookbookRepository;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.CookbookService;
import com.example.cookbook.service.CookbookServiceImpl;
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

class CookbookServiceImplTest {

    @Mock
    private CookbookRepository cookbookRepository;

    @Mock
    private RecipeRepository recipeRepository;

    private CookbookService cookbookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cookbookService = new CookbookServiceImpl(recipeRepository, cookbookRepository);
    }

    @Test
    void saveCookbook_ValidCookbook_ReturnsSavedCookbook() {
        // Arrange
        Cookbook cookbook = new Cookbook();
        when(cookbookRepository.save(cookbook)).thenReturn(cookbook);

        // Act
        Cookbook savedCookbook = cookbookService.save(cookbook);

        // Assert
        assertNotNull(savedCookbook);
        assertEquals(cookbook, savedCookbook);
        verify(cookbookRepository, times(1)).save(cookbook);
    }

    @Test
    void findById_ExistingId_ReturnsCookbook() {
        // Arrange
        Long cookbookId = 1L;
        Cookbook cookbook = new Cookbook();
        when(cookbookRepository.findById(cookbookId)).thenReturn(Optional.of(cookbook));

        // Act
        Cookbook foundCookbook = cookbookService.findById(cookbookId);

        // Assert
        assertNotNull(foundCookbook);
        assertEquals(cookbook, foundCookbook);
        verify(cookbookRepository, times(1)).findById(cookbookId);
    }

    @Test
    void findById_NonExistingId_ThrowsNoSuchElementException() {
        // Arrange
        Long cookbookId = 1L;
        when(cookbookRepository.findById(cookbookId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> cookbookService.findById(cookbookId));
        verify(cookbookRepository, times(1)).findById(cookbookId);
    }

    @Test
    void deleteById_ExistingId_DeletesCookbook() {
        // Arrange
        Long cookbookId = 1L;

        // Act
        cookbookService.deleteById(cookbookId);

        // Assert
        verify(cookbookRepository, times(1)).deleteById(cookbookId);
    }

    @Test
    void findAllRecipes_ValidCookbookId_ReturnsListOfRecipes() {
        // Arrange
        Long cookbookId = 1L;
        Cookbook cookbook = new Cookbook();
        cookbook.setId(cookbookId);

        Recipe recipe1 = new Recipe();
        recipe1.setCookbook(cookbook);

        Recipe recipe2 = new Recipe();
        recipe2.setCookbook(cookbook);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(recipes);

        // Act
        List<Recipe> foundRecipes = cookbookService.findAllRecipes(PageRequest.of(0, 10), cookbookId);

        // Assert
        assertNotNull(foundRecipes);
        assertEquals(recipes.size(), foundRecipes.size());
        assertTrue(foundRecipes.contains(recipe1));
        assertTrue(foundRecipes.contains(recipe2));
        verify(recipeRepository, times(1)).findAll();
    }
}
