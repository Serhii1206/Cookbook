package com.example.cookbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cookbook.model.Cookbook;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {
}