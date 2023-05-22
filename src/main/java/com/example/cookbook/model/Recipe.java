package com.example.cookbook.model;

import java.time.LocalDateTime;
import javax.persistence.*;
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
