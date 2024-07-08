package com.example.json.service.impl;

import com.example.json.constant.ProductCategory;
import com.example.json.dto.ProductQueryParams;
import com.example.json.dto.ProductRequest;
import com.example.json.entity.Product;
import com.example.json.repository.ProductRepository;
import com.example.json.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    ProductRepository productRepository;
    @Override
    public Product getProductById(Integer productId) {
        Optional<Product> product = productRepository.findByProductId(productId);

        return product.orElse(null);
    }

    @Override
    public List<Product> getProductList() {

        return productRepository.findAll();
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

        Optional<Product> findProduct = productRepository.findByProductId(productId);

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription("test");
        product.setCreateDate(findProduct.isPresent()?findProduct.get().getCreateDate():now);
        product.setLastModifiedDate(now);

        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductListByCondition(ProductQueryParams productQueryParams) {

        Sort sort;

        if(StringUtils.isEmpty(productQueryParams.getSort())){
            sort = Sort.by(Sort.Direction.ASC, "productId");
        }else{
            sort = Sort.by(Sort.Direction.DESC, "productId");
        }
        return productRepository.findAll(buildProductSearchCondition(productQueryParams), sort);
    }

    private Specification<Product> buildProductSearchCondition(ProductQueryParams productQueryParams) {
        //重写toPredicate方法
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            String search = productQueryParams.getSearch();
            ProductCategory category = productQueryParams.getCategory();


            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.isNotBlank(search)) {
                // 本处我都转为小写，进行模糊匹配
                predicateList.add(cb.like(cb.lower(root.get("productName").as(String.class)), "%" + search.toLowerCase() + "%"));
            }

            Optional.of(category).ifPresent(c -> {
                if (StringUtils.isNotBlank(c.name())) {
                    predicateList.add(cb.equal(root.get("category").as(String.class), c.name()));
                }
            });


            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
