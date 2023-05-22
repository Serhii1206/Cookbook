package com.example.cookbook.dto.request;

import lombok.Data;

@Data
public class RecipeRequestDto {
    private Long parentId;
    private String name;
    private String description;
    private Long cookbookId;
}
