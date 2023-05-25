package com.example.cookbook.controller;

import com.example.cookbook.dto.mapper.RequestDtoMapper;
import com.example.cookbook.dto.mapper.ResponseDtoMapper;
import com.example.cookbook.dto.request.CookbookRequestDto;
import com.example.cookbook.dto.response.CookbookResponseDto;
import com.example.cookbook.dto.response.RecipeResponseDto;
import com.example.cookbook.model.Cookbook;
import com.example.cookbook.model.Recipe;
import com.example.cookbook.service.CookbookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/cookbooks")
public class CookbookController {
    private final CookbookService cookbookService;
    private final ResponseDtoMapper<CookbookResponseDto, Cookbook>
            cookbookResponseDtoMapper;
    private final RequestDtoMapper<CookbookRequestDto, Cookbook>
            cookbookRequestDtoMapper;
    private final ResponseDtoMapper<RecipeResponseDto, Recipe>
            recipeResponseDtoMapper;

    @PostMapping
    public CookbookResponseDto save(@RequestBody CookbookRequestDto cookbookRequestDto) {
        Cookbook cookbook = cookbookRequestDtoMapper.mapToModel(cookbookRequestDto);
        cookbookService.save(cookbook);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @GetMapping("/{id}")
    public CookbookResponseDto findById(@PathVariable Long id) {
        Cookbook cookbook = cookbookService.findById(id);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @PutMapping("/{id}")
    public CookbookResponseDto changeNameOfCookbook(@PathVariable Long id,
                                    @RequestBody CookbookRequestDto cookbookRequestDto) {
        Cookbook cookbook = cookbookService.findById(id);
        cookbook.setName(cookbookRequestDto.getName());
        cookbookService.save(cookbook);
        return cookbookResponseDtoMapper.mapToDto(cookbook);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cookbookService.deleteById(id);
    }

    @GetMapping("/recipes/{id}")
    public List<RecipeResponseDto> findAllRecipes(@PathVariable Long id,
                                                  @RequestParam(defaultValue = "5") Integer count,
                                                  @RequestParam (defaultValue = "0") Integer page,
                                                  @RequestParam (defaultValue = "name")
                                                      String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(count, page, sort);
        return cookbookService.findAllRecipes(pageRequest, id)
                .stream()
                .map(recipeResponseDtoMapper::mapToDto)
                .toList();
    }
}
