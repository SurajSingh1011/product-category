package com.example.product_category.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.ManyToOne;

//@Data

public class ProductDto extends ProductDtoPost {


    @ManyToOne
    private CategoryDtoPost categoryDtoPost;

    public CategoryDtoPost getCategoryDtoPost() {
        return categoryDtoPost;
    }

    public void setCategoryDtoPost(CategoryDtoPost categoryDtoPost) {
        this.categoryDtoPost = categoryDtoPost;
    }

    public ProductDto(CategoryDtoPost categoryDtoPost) {
        this.categoryDtoPost = categoryDtoPost;
    }

    public ProductDto() {
    }
}
