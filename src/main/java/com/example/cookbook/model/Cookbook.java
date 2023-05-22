package com.example.cookbook.model;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cookbooks")
public class Cookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
