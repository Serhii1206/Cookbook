package com.example.cookbook.dto.response;

import com.example.cookbook.model.Recipe;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private Recipe parentRecipe;
    private CookbookResponseDto cookbookResponseDto;
    private LocalDateTime publicationDate;
    private String name;
    private String description;
}
