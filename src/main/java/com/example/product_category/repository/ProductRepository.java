package com.example.product_category.repository;

import com.example.product_category.entity.Category;
import com.example.product_category.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int i);

    Optional<Product> findByName(String name);


    Optional<List<Product>> findByCategoryCode(int categoryCode);
}
