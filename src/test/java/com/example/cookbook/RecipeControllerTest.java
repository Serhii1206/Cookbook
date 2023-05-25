//package com.example.cookbook;
//import com.example.cookbook.controller.RecipeController;
//import com.example.cookbook.dto.request.RecipeRequestDto;
//import com.example.cookbook.dto.response.RecipeResponseDto;
//import com.example.cookbook.model.Recipe;
//import com.example.cookbook.service.RecipeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class RecipeControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private RecipeService recipeService;
//
//    private RecipeController recipeController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        recipeController = new RecipeController(recipeService);
//        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
//    }
//
//    @Test
//    void save_ValidRecipe_ReturnsSavedRecipe() throws Exception {
//        // Arrange
//        RecipeRequestDto requestDto = new RecipeRequestDto();
//        Recipe recipe = new Recipe();
//        RecipeResponseDto responseDto = new RecipeResponseDto();
//        when(recipeService.save(any(Recipe.class))).thenReturn(recipe);
//        when(recipeResponseDtoMapper.mapToDto(recipe)).thenReturn(responseDto);
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/recipes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonUtil.toJson(requestDto)))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(200, statusCode);
//        RecipeResponseDto savedRecipeDto = JsonUtil.fromJson(result.getResponse().getContentAsString(), RecipeResponseDto.class);
//        assertNotNull(savedRecipeDto);
//        assertEquals(responseDto, savedRecipeDto);
//        verify(recipeService, times(1)).save(any(Recipe.class));
//    }
//
//    @Test
//    void findById_ExistingId_ReturnsRecipe() throws Exception {
//        // Arrange
//        Long recipeId = 1L;
//        Recipe recipe = new Recipe();
//        RecipeResponseDto responseDto = new RecipeResponseDto();
//        when(recipeService.findById(recipeId)).thenReturn(recipe);
//        when(recipeResponseDtoMapper.mapToDto(recipe)).thenReturn(responseDto);
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/recipes/{id}", recipeId))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(200, statusCode);
//        RecipeResponseDto foundRecipeDto = JsonUtil.fromJson(result.getResponse().getContentAsString(), RecipeResponseDto.class);
//        assertNotNull(foundRecipeDto);
//        assertEquals(responseDto, foundRecipeDto);
//        verify(recipeService, times(1)).findById(recipeId);
//    }
//
//    @Test
//    void findById_NonExistingId_ReturnsNotFound() throws Exception {
//        // Arrange
//        Long recipeId = 1L;
//        when(recipeService.findById(recipeId)).thenThrow(new NoSuchElementException());
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/recipes/{id}", recipeId))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(404, statusCode);
//        verify(recipeService, times(1)).findById(recipeId);
//    }
//
//    @Test
//    void update_ExistingId_ReturnsUpdatedRecipe() throws Exception {
//        // Arrange
//        Long recipeId = 1L;
//        RecipeRequestDto requestDto = new RecipeRequestDto();
//        Recipe recipe = new Recipe();
//        RecipeResponseDto responseDto = new RecipeResponseDto();
//        when(recipeService.findById(recipeId)).thenReturn(recipe);
//        when(recipeResponseDtoMapper.mapToDto(recipe)).thenReturn(responseDto);
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/recipes/{id}", recipeId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JsonUtil.toJson(requestDto)))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(200, statusCode);
//        RecipeResponseDto updatedRecipeDto = JsonUtil.fromJson(result.getResponse().getContentAsString(), RecipeResponseDto.class);
//        assertNotNull(updatedRecipeDto);
//        assertEquals(responseDto, updatedRecipeDto);
//        verify(recipeService, times(1)).findById(recipeId);
//        verify(recipeService, times(1)).save(recipe);
//    }
//
//    @Test
//    void deleteById_ExistingId_ReturnsNoContent() throws Exception {
//        // Arrange
//        Long recipeId = 1L;
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/recipes/{id}", recipeId))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(204, statusCode);
//        verify(recipeService, times(1)).deleteById(recipeId);
//    }
//
//    @Test
//    void findVersionsOfRecipe_ValidId_ReturnsListOfRecipes() throws Exception {
//        // Arrange
//        Long recipeId = 1L;
//        RecipeResponseDto responseDto1 = new RecipeResponseDto();
//        RecipeResponseDto responseDto2 = new RecipeResponseDto();
//        List<RecipeResponseDto> responseDtos = new ArrayList<>();
//        responseDtos.add(responseDto1);
//        responseDtos.add(responseDto2);
//        when(recipeService.findVersionsOfRecipe(any(PageRequest.class), eq(recipeId))).thenReturn(new ArrayList<>());
//        when(recipeResponseDtoMapper.mapToDto(any(Recipe.class))).thenReturn(responseDto1, responseDto2);
//
//        // Act
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/recipes/{id}/versions", recipeId)
//                        .param("count", "5")
//                        .param("page", "0")
//                        .param("sortBy", "name"))
//                .andReturn();
//
//        // Assert
//        int statusCode = result.getResponse().getStatus();
//        assertEquals(200, statusCode);
//        List<RecipeResponseDto> foundRecipes = JsonUtil.fromJsonToList(result.getResponse().getContentAsString(), RecipeResponseDto.class);
//        assertNotNull(foundRecipes);
//        assertEquals(responseDtos.size(), foundRecipes.size());
//        assertEquals(responseDto1, foundRecipes.get(0));
//        assertEquals(responseDto2, foundRecipes.get(1));
//        verify(recipeService, times(1)).findVersionsOfRecipe(any(PageRequest.class), eq(recipeId));
//    }
//}
