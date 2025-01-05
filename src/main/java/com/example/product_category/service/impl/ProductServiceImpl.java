package com.example.product_category.service.impl;

import com.example.product_category.dto.CategoryDto;
import com.example.product_category.dto.CategoryDtoPost;
import com.example.product_category.dto.ProductDto;
import com.example.product_category.dto.ProductDtoPost;
import com.example.product_category.entity.Category;
import com.example.product_category.entity.Product;
import com.example.product_category.exception.ResourceAlreadyExistsException;
import com.example.product_category.exception.ResourceNotFoundException;
import com.example.product_category.mapper.CategoryMapper;
import com.example.product_category.mapper.ProductMapper;
import com.example.product_category.repository.CategoryRepository;
import com.example.product_category.repository.ProductRepository;
import com.example.product_category.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ProductDto fetchProduct(int Id) {
        Product product=productRepository.findById(Id).orElseThrow(
                ()-> new ResourceNotFoundException("Product","ProductID",String.valueOf(Id))
        );
        Category category=categoryRepository.findById(product.getCategoryCode()).orElseThrow(
                () -> new ResourceNotFoundException("Category","CategoryCode",String.valueOf(product.getCategoryCode()))
        );
        ProductDto productDto= ProductMapper.mapToProductDto(product,new ProductDto());
        CategoryDtoPost categoryDtoPost=CategoryMapper.mapToCategoryDtoPost(category,new CategoryDtoPost());

        productDto.setCategoryDtoPost(categoryDtoPost);

        return productDto;
    }

    @Override
    public void createProduct(ProductDtoPost productDtoPost) {
        Product product=ProductMapper.mapToProduct(productDtoPost,new Product());
        Optional<Product> optionalProduct=productRepository.findByName(product.getName());
        Optional<Category> optionalCategory=categoryRepository.findByCategoryCode(product.getCategoryCode());
        if(optionalProduct.isPresent()){
            throw new ResourceAlreadyExistsException("Product already exists with given name"+productDtoPost.getName());
        }
        if(optionalCategory.isPresent()) {
            Product saveProduct = productRepository.save(product);
        }
        else {
            throw new ResourceNotFoundException("Category","CategoryCode",String.valueOf(product.getCategoryCode()));
        }

    }

    @Override
    public void createCategory(CategoryDtoPost categoryDtoPost) {
        Category category=CategoryMapper.mapToCategory(categoryDtoPost,new Category());
        Optional<Category> optionalCategory=categoryRepository.findByName(category.getName());
        if(optionalCategory.isPresent()){
            throw new ResourceAlreadyExistsException("Category already exists with given name "+categoryDtoPost.getName());
        }
        Category saveCategory=categoryRepository.save(category);
    }

    @Override
    public CategoryDto fetchCategory(int id) {
        Category category=categoryRepository.findByCategoryCode(id).orElseThrow(
                ()->new ResourceNotFoundException("Category","CategoryCode",String.valueOf(id))
        );
        CategoryDto categoryDto=CategoryMapper.mapToCategoryDto(category,new CategoryDto());
        Optional<List<Product>> products = productRepository.findByCategoryCode(category.getCategoryCode());
        categoryDto.setProductDtoList(products.stream()
                                              .flatMap(List::stream)
                                              .map(product -> ProductMapper.mapToProductDtoPost(product,new ProductDtoPost()))
                                              .collect(Collectors.toList()));
        return categoryDto;
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product=productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product","ProductID",String.valueOf(id))
        );
        productRepository.deleteById(product.getId());
        return true;
    }

    @Override
    @Transactional
    public boolean deleteCategory(int id) {
        Category category=categoryRepository.findByCategoryCode(id).orElseThrow(
                () -> new ResourceNotFoundException("Category","categoryId",String.valueOf(id))
        );
        Optional<List<Product>> product = productRepository.findByCategoryCode(category.getCategoryCode());
        if(product.isPresent()){
            throw new ResourceAlreadyExistsException("Products are linked to this category.Delete the products with categorycode "+category.getCategoryCode());
        }
        else{
            categoryRepository.deleteByCategoryCode(category.getCategoryCode());
        }
        return true;
    }

    @Override
    public boolean updateProduct(int id, ProductDtoPost productDtoPost) {
        Product product=ProductMapper.mapToProduct(productDtoPost,new Product());
        Optional<Product> optionalProduct=productRepository.findById(id);
        Optional<Category> optionalCategory=categoryRepository.findByCategoryCode(product.getCategoryCode());
        if(optionalProduct.isPresent() && optionalCategory.isPresent()){
            Product existingProduct=optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setDesc(product.getDesc());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategorycode(product.getCategoryCode());
            Product updateProduct = productRepository.save(existingProduct);
        }
        else {
            throw new ResourceNotFoundException("Product or Category","Product ID or Category Code",String.valueOf(id));
        }
        return true;
    }

    @Override
    public boolean updateCategory(int id, CategoryDtoPost categoryDtoPost) {
        Category category=CategoryMapper.mapToCategory(categoryDtoPost,new Category());
        Optional<Category> optionalCategory=categoryRepository.findByCategoryCode(id);
        if(optionalCategory.isPresent()){
            Category existingCategory=optionalCategory.get();
            existingCategory.setName(category.getName());
            existingCategory.setDesc(category.getDesc());
            Category updateCategory= categoryRepository.save(existingCategory);
        }
        else {
            throw new ResourceNotFoundException("Category","Category Code",String.valueOf(id));
        }
        return true;
    }

    @Override
    public Page<Product> getAllProduct(int page) {
        Pageable pageable = PageRequest.of(page, 5,Sort.by("id").ascending());
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Category> getAllCategory(int page) {
        Pageable pageable = PageRequest.of(page, 5,Sort.by("categoryCode").ascending());
        return categoryRepository.findAll(pageable);
    }


}
