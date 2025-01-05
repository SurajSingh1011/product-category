package com.example.product_category.service;

import com.example.product_category.dto.CategoryDto;
import com.example.product_category.dto.CategoryDtoPost;
import com.example.product_category.dto.ProductDto;
import com.example.product_category.dto.ProductDtoPost;
import com.example.product_category.entity.Category;
import com.example.product_category.entity.Product;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;

public interface IProductService {
    public ProductDto fetchProduct(int id);

    void createProduct(@Valid ProductDtoPost productDtoPost);

    void createCategory(@Valid CategoryDtoPost categoryDtoPost);

    CategoryDto fetchCategory(int id);

    boolean deleteProduct(int id);

    boolean deleteCategory(int id);


    boolean updateProduct(int id, @Valid ProductDtoPost productDtoPost);

    boolean updateCategory(int id, @Valid CategoryDtoPost categoryDtoPost);

    Page<Product> getAllProduct(int page);

    Page<Category> getAllCategory(int page);
}
