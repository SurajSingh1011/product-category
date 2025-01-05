package com.example.product_category.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Data
public class ProductDtoPost {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the product name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Price can not be a null or empty")
    private String price;

    @NotEmpty(message = "Describe the product")
    private String description;

    @NotNull(message = "CategoryCode is mandatory")
    private int categoryCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public ProductDtoPost(String name, String price, String description, int categoryCode) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryCode = categoryCode;
    }

    public ProductDtoPost() {
    }
}
