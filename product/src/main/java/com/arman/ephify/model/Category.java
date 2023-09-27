package com.arman.ephify.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String description; 
    private String image; 
    private String seoMetaTitle; 
    private String seoMetaDescription; 
    private String seoMetaKeywords; 
    private String urlSlug; 

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory; // for hierarchical structure
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Getters and setters
}
