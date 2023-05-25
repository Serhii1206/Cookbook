package com.example.cookbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_recipe_id")
    private Recipe parentRecipe;
    @ManyToOne
    @JoinColumn(name = "cookbook_id")
    private Cookbook cookbook;
    @CreationTimestamp
    private LocalDateTime publicationDate;
    private String name;
    private String description;
}
