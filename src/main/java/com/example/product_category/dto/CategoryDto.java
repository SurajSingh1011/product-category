package com.example.product_category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

//@Data
public class CategoryDto extends CategoryDtoPost {


    @OneToMany(cascade= CascadeType.ALL, mappedBy="categoryDto")
    private List<ProductDtoPost> productDtoList;

    public List<ProductDtoPost> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDtoPost> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public CategoryDto(List<ProductDtoPost> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public CategoryDto() {
    }
}
