package com.example.cookbook.repository;

import com.example.cookbook.model.Cookbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookbookRepository extends JpaRepository<Cookbook, Long> {
}
