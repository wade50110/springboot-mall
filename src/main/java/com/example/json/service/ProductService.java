package com.example.json.service;

import com.example.json.dto.ProductRequest;
import com.example.json.model.Product;

public interface ProductService {

    public Product getProductById(Integer productId);

    public Product createProduct(ProductRequest productRequest);

    public Product updateProduct(Integer productId,ProductRequest productRequest);

    public void deleteProductById(Integer productId);
}
