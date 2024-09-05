package com.example.json.service;

import com.example.json.dto.ProductQueryParams;
import com.example.json.dto.ProductRequest;
import com.example.json.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    List<Product> getProductList();

    List<Product> getProductListByCondition(ProductQueryParams productQueryParams);

    Product createProduct(ProductRequest productRequest);

    Product updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
