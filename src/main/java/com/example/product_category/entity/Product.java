package com.example.product_category.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="categorycode", nullable = false)
    private int categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategorycode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Product(int id, String name, Double price, String desc, int categoryCode) {
        id = id;
        this.name = name;
        this.price = price;
        this.description = desc;
        this.categoryCode = categoryCode;
    }

    public Product() {
    }
}
