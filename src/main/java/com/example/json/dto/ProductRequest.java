package com.example.json.dto;

import com.example.json.constant.ProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    @Schema(description = "查詢分頁結果的頁數", example = "1")
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
