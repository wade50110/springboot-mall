package com.example.json.dto;

import com.example.json.constant.ProductCategory;
import com.example.json.model.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    private Integer productId;

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;
}
