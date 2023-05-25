//package com.example.cookbook;
//
//import com.example.cookbook.controller.CookbookController;
//import com.example.cookbook.dto.request.CookbookRequestDto;
//import com.example.cookbook.dto.response.CookbookResponseDto;
//import com.example.cookbook.dto.response.RecipeResponseDto;
//import com.example.cookbook.model.Cookbook;
//import com.example.cookbook.model.Recipe;
//import com.example.cookbook.service.CookbookService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//public class CookbookControllerTest {
//
//    @Mock
//    private CookbookService cookbookService;
//
//    private CookbookController cookbookController;
//    private CookbookRequestDto cookbookRequestDto;
//    private Cookbook cookbook;
//    private CookbookResponseDto cookbookResponseDto;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        cookbookController = new CookbookController(cookbookService, null, null, null);
//
//        cookbookRequestDto = new CookbookRequestDto();
//        cookbookRequestDto.setName("My Cookbook");
//
//        cookbook = new Cookbook();
//        cookbook.setId(1L);
//        cookbook.setName("My Cookbook");
//
//        cookbookResponseDto = new CookbookResponseDto();
//        cookbookResponseDto.setId(1L);
//        cookbookResponseDto.setName("My Cookbook");
//    }
//
//    @Test
//    public void testSave() {
//        // Arrange
//        when(cookbookService.save(any(Cookbook.class))).thenReturn(cookbook);
//
//        // Act
//        CookbookResponseDto savedCookbook = cookbookController.save(cookbookRequestDto);
//
//        // Assert
//        assertEquals(cookbookResponseDto, savedCookbook);
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        when(cookbookService.findById(anyLong())).thenReturn(cookbook);
//
//        // Act
//        CookbookResponseDto foundCookbook = cookbookController.findById(1L);
//
//        // Assert
//        assertEquals(cookbookResponseDto, foundCookbook);
//    }
//
//    @Test
//    public void testChangeNameOfCookbook() {
//        // Arrange
//        when(cookbookService.findById(anyLong())).thenReturn(cookbook);
//        when(cookbookService.save(any(Cookbook.class))).thenReturn(cookbook);
//
//        // Act
//        CookbookResponseDto changedCookbook = cookbookController.changeNameOfCookbook(1L, cookbookRequestDto);
//
//        // Assert
//        assertEquals(cookbookResponseDto, changedCookbook);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // No Arrange necessary
//
//        // Act
//        cookbookController.deleteById(1L);
//
//        // Assert
//        // Verify that the cookbookService.deleteById method is called
//    }
//
//    @Test
//    public void testFindAllRecipes() {
//        // Arrange
//        List<Recipe> recipes = Collections.singletonList(new Recipe());
//        when(cookbookService.findAllRecipes(any(PageRequest.class), anyLong())).thenReturn(recipes);
//
//        // Act
//        List<RecipeResponseDto> foundRecipes = cookbookController.findAllRecipes(1L, 5, 0, "name");
//
//        // Assert
//        assertEquals(recipes.size(), foundRecipes.size());
//        // Additional assertions for the mapped RecipeResponseDto objects
//    }
//}
