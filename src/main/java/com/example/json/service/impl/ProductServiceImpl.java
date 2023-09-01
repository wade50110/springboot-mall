package com.example.json.service.impl;

import com.example.json.model.Product;
import com.example.json.repository.ProductRepository;
import com.example.json.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findByProductId(productId);
    }
}
