package com.example.json.model;

import com.example.json.constant.ProductCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_Name")
    private String productName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Date createDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}