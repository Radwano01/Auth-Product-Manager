package com.example.ecommerce.Entities;


import jakarta.persistence.*;


@Entity
@Table
public class ProductEntity {

    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "product_sequence"
    )

    private int id;
    private String name;
    private String category;
    private String description;
    private Integer price;

    public ProductEntity(){};

    public ProductEntity(Integer id, String name, String category, String desc, Integer price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = desc;
        this.price = price;
    };

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
