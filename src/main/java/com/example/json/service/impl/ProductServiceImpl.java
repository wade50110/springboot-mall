package com.example.json.service.impl;

import com.example.json.dto.ProductRequest;
import com.example.json.model.Product;
import com.example.json.repository.ProductRepository;
import com.example.json.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {

        Date now = new Date();

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription("test");
        product.setCreateDate(now);
        product.setLastModifiedDate(now);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer productId, ProductRequest productRequest) {

        Date now = new Date();

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription("test");
        product.setCreateDate(productRepository.findByProductId(productId).getCreateDate());
        product.setLastModifiedDate(now);

        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }
}
