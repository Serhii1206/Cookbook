package com.example.cookbook.service;

import com.example.cookbook.model.Cookbook;
import com.example.cookbook.model.Recipe;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface CookbookService {
    Cookbook save(Cookbook cookbook);

    Cookbook findById(Long id);

    void deleteById(Long id);

    List<Recipe> findAllRecipes(PageRequest pageRequest, Long id);
}
