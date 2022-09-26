package com.eosa.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.CategoryDTO;

@Mapper
public interface CategoryMapper {

    List<CategoryDTO> selectCategory();

    int priceUpdateCategory(CategoryDTO categoryDTO);

    int deleteCategory(Long categoryIdx);

}
