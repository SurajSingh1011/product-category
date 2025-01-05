package com.example.product_category.mapper;

import com.example.product_category.dto.ProductDto;
import com.example.product_category.dto.ProductDtoPost;
import com.example.product_category.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product,ProductDto productDto){
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice().toString());
        productDto.setDescription(product.getDesc());
        productDto.setCategoryCode(product.getCategoryCode());
        return productDto;
    }
    public static Product mapToProduct(ProductDto productDto,Product product){
        product.setName(productDto.getName());
        product.setPrice(Double.valueOf(productDto.getPrice()));
        product.setDesc(productDto.getDescription());
        product.setCategorycode(productDto.getCategoryCode());
        return product;
    }

    public static ProductDtoPost mapToProductDtoPost(Product product, ProductDtoPost productDtoPost){
        productDtoPost.setName(product.getName());
        productDtoPost.setPrice(product.getPrice().toString());
        productDtoPost.setDescription(product.getDesc());
        productDtoPost.setCategoryCode(product.getCategoryCode());
        return productDtoPost;
    }

    public static Product mapToProduct(ProductDtoPost productDtoPost,Product product){
        product.setName(productDtoPost.getName());
        product.setPrice(Double.valueOf(productDtoPost.getPrice()));
        product.setDesc(productDtoPost.getDescription());
        product.setCategorycode(productDtoPost.getCategoryCode());
        return product;
    }
}
