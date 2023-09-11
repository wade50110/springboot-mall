package com.example.json.service;

import com.example.json.dto.ProductQueryParams;
import com.example.json.dto.ProductRequest;
import com.example.json.model.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Integer productId);

    public List<Product> getProductList();

    public List<Product> getProductListByCondition(ProductQueryParams productQueryParams);

    public Product createProduct(ProductRequest productRequest);

    public Product updateProduct(Integer productId,ProductRequest productRequest);

    public void deleteProductById(Integer productId);
}
