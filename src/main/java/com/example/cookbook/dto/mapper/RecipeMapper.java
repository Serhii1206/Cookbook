package com.example.cookbook.dto.mapper;

import com.example.cookbook.dto.request.RecipeRequestDto;
import com.example.cookbook.dto.response.CookbookResponseDto;
import com.example.cookbook.dto.response.RecipeResponseDto;
import com.example.cookbook.model.Cookbook;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.service.CookbookService;
import com.example.cookbook.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RecipeMapper implements ResponseDtoMapper<RecipeResponseDto, Recipe>,
        RequestDtoMapper<RecipeRequestDto, Recipe> {
    private final CookbookService cookbookService;
    private final RecipeService recipeService;
    private final ResponseDtoMapper<CookbookResponseDto, Cookbook>
            cookbookResponseDtoMapper;

    @Override
    public Recipe mapToModel(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setParentRecipe(recipeService.findById(dto.getParentId()));
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setCookbook(cookbookService.findById(dto.getCookbookId()));
        return recipe;
    }

    @Override
    public RecipeResponseDto mapToDto(Recipe recipe) {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setId(recipe.getId());
        recipeResponseDto.setParentRecipe(recipe.getParentRecipe());
        recipeResponseDto.setCookbookResponseDto(
                cookbookResponseDtoMapper.mapToDto(recipe.getCookbook()));
        recipeResponseDto.setPublicationDate(recipe.getPublicationDate());
        recipeResponseDto.setName(recipe.getName());
        recipeResponseDto.setDescription(recipe.getDescription());
        return recipeResponseDto;
    }
}
