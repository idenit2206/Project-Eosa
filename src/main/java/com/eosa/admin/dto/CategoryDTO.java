package com.eosa.admin.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    
    private Long categoryIdx;
    private String categoryName;
    private int categoryPrice;

    public CategoryDTO(Long categoryIdx, String categoryName, int categoryPrice) {
        this.categoryIdx = categoryIdx;
        this.categoryName = categoryName;
        this.categoryPrice = categoryPrice;
    }
    
}
