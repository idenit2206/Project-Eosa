package com.eosa.admin.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    
    private Long categoryIdx;
    private String categoryName;
    private int categoryPrice;
    private String categoryIcon;
    private String categoryIconName;

    public CategoryDTO() {}

    public CategoryDTO(String categoryName, int categoryPrice) {
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }

    public CategoryDTO(Long categoryIdx, String categoryName, int categoryPrice) {
        this.categoryIdx = categoryIdx;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }

    public CategoryDTO(String categoryName, int categoryPrice, String categoryIcon, String categoryIconName) {
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
        this.categoryIcon = categoryIcon;
        this.categoryIconName = categoryIconName;
    }

    public CategoryDTO(Long categoryIdx, String categoryName, int categoryPrice, String categoryIcon, String categoryIconName) {
        this.categoryIdx = categoryIdx;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
        this.categoryIcon = categoryIcon;
        this.categoryIconName = categoryIconName;
    }
    
}
