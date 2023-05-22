package com.example.cookbook.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import com.example.cookbook.model.Cookbook;
import com.example.cookbook.model.Recipe;

public interface CookbookService {
    Cookbook save(Cookbook cookbook);

    Cookbook findById(Long id);

    void deleteById(Long id);

    List<Recipe> findAllRecipes(PageRequest pageRequest, Long id);
}