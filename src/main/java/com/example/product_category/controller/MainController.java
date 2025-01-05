package com.example.product_category.controller;

import com.example.product_category.dto.CategoryDto;
import com.example.product_category.dto.CategoryDtoPost;
import com.example.product_category.dto.ProductDto;
import com.example.product_category.dto.ProductDtoPost;
import com.example.product_category.entity.Category;
import com.example.product_category.entity.Product;
import com.example.product_category.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class MainController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("/products/{Id}")
    public ResponseEntity<ProductDto> fetchProductDetails(@PathVariable
                                                           int Id) {
        ProductDto productDto = iProductService.fetchProduct(Id);
        return ResponseEntity.status(HttpStatus.FOUND).body(productDto);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDtoPost> createProduct(@Valid @RequestBody ProductDtoPost productDtoPost){
        iProductService.createProduct(productDtoPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoPost);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDtoPost> createCategory(@Valid @RequestBody CategoryDtoPost categoryDtoPost){
        iProductService.createCategory(categoryDtoPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDtoPost);
    }
    @GetMapping("/categories/{Id}")
    public ResponseEntity<CategoryDto> fetchCategoryDetails(@PathVariable
                                                          int Id) {
        CategoryDto categoryDto = iProductService.fetchCategory(Id);
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryDto);
    }

    @DeleteMapping("/products/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int Id) {
        boolean isDeleted = iProductService.deleteProduct(Id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Product Deleted Successfully");
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Delete Operation Failed. Check once again!!!");
        }
    }

    @DeleteMapping("/categories/{Id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int Id) {
        boolean isDeleted = iProductService.deleteCategory(Id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Category Deleted Successfully");
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Delete Operation Failed. Check once again!!!");
        }
    }

    @PutMapping("/products/{Id}")
    public ResponseEntity<String> updateProduct(@PathVariable int Id,@Valid @RequestBody ProductDtoPost productDtoPost){
        boolean isUpdated=iProductService.updateProduct(Id,productDtoPost);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Product Updated Successfully");
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Update Operation Failed. Check once again!!!");
        }
    }

    @PutMapping("/categories/{Id}")
    public ResponseEntity<String> updateCategory(@PathVariable int Id,@Valid @RequestBody CategoryDtoPost categoryDtoPost){
        boolean isUpdated=iProductService.updateCategory(Id,categoryDtoPost);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Category Updated Successfully");
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body("Update Operation Failed. Check once again!!!");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "page",defaultValue = "0") int page){
        Page<Product> productList=iProductService.getAllProduct(page);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productList.getContent());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(value = "page",defaultValue = "0") int page){
        Page<Category> categoryList=iProductService.getAllCategory(page);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryList.getContent());
    }
}
