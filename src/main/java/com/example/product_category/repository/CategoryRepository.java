package com.example.product_category.repository;

import com.example.product_category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    Optional<Category> findByCategoryCode(int categoryCode);

    Optional<Category> findByName(String name);

    void deleteByCategoryCode(int categoryCode);
}
