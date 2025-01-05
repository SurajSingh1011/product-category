package com.example.product_category.mapper;

import com.example.product_category.dto.CategoryDto;
import com.example.product_category.dto.CategoryDtoPost;
import com.example.product_category.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category,CategoryDto categoryDto){
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDesc());
        return categoryDto;
    }
    public static Category mapToCategory(CategoryDto categoryDto,Category category){
        category.setName(categoryDto.getName());
        category.setDesc(categoryDto.getDescription());
        return category;
    }

    public static CategoryDtoPost mapToCategoryDtoPost(Category category, CategoryDtoPost categoryDtoPost){
        categoryDtoPost.setName(category.getName());
        categoryDtoPost.setDescription(category.getDesc());
        return categoryDtoPost;
    }

    public static Category mapToCategory(CategoryDtoPost categoryDtoPost,Category category){
        category.setName(categoryDtoPost.getName());
        category.setDesc(categoryDtoPost.getDescription());
        return category;
    }
}
