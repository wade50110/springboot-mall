package com.example.json.controller;

import com.example.json.constant.ProductCategory;
import com.example.json.dto.ProductQueryParams;
import com.example.json.dto.ProductRequest;
import com.example.json.model.Product;
import com.example.json.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductList(){
        List<Product> product = productService.getProductList();

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/productByCondition")
    public ResponseEntity<List<Product>> getProductListByCondition(
            ProductQueryParams productQueryParams
    ){
        List<Product> product = productService.getProductListByCondition(productQueryParams);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,@RequestBody @Valid ProductRequest productRequest){

        Product product = productService.updateProduct(productId,productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}