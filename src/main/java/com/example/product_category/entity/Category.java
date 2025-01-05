package com.example.product_category.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorycode")
    private int categoryCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public Category(int categoryCode, String name, String description) {
        this.categoryCode = categoryCode;
        this.name = name;
        this.description = description;
    }

    public Category() {
    }
}
