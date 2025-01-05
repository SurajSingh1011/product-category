package com.example.product_category.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDtoPost {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the product name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Describe the product")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDtoPost(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryDtoPost() {
    }
}
