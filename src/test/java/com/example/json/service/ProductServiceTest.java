package com.example.json.service;

import com.example.json.constant.ProductCategory;
import com.example.json.dto.ProductQueryParams;
import com.example.json.dto.ProductRequest;
import com.example.json.entity.Product;
import com.example.json.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Resource
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    public void testGetProductById() {
        // [Arrange] 預期資料
        Product exceptProduct = new Product();
        exceptProduct.setProductId(1);
        exceptProduct.setProductName("ProductName");
        exceptProduct.setCategory(ProductCategory.FOOD);
        exceptProduct.setPrice(100);
        exceptProduct.setStock(100);
        exceptProduct.setDescription("Description");
        exceptProduct.setImageUrl("ImageUrl");


        Mockito.when(productRepository.findByProductId(1)).thenReturn(Optional.of(exceptProduct));

        // [Act] 執行測試
        Product result = productService.getProductById(1);

        // [Assert] 驗證結果
        assertEquals("testGetProductById",exceptProduct, result);
    }

    @Test
    public void testGetProductList() {
        // [Arrange] 預期資料
        Product product = new Product();
        product.setProductId(3);
        product.setProductName("ProductName");
        product.setCategory(ProductCategory.FOOD);
        product.setPrice(100);
        product.setStock(100);
        product.setDescription("Description");
        product.setImageUrl("ImageUrl");

        Product product2 = new Product();
        product2.setProductId(3);
        product2.setProductName("ProductName");
        product2.setCategory(ProductCategory.FOOD);
        product2.setPrice(100);
        product2.setStock(100);
        product2.setDescription("Description");
        product2.setImageUrl("ImageUrl");

        List<Product> exceptProductList = List.of(product,product2);

        Mockito.when(productRepository.findAll()).thenReturn(exceptProductList);

        // [Act] 執行測試
        List<Product> result = productService.getProductList();

        // [Assert] 驗證結果
        assertEquals("testGetProductList",exceptProductList, result);
    }

    @Test
    public void testGetProductListByCondition() {
        // [Arrange] 預期資料
        Product product = new Product();
        product.setProductId(3);
        product.setProductName("ProductName");
        product.setCategory(ProductCategory.FOOD);
        product.setPrice(100);
        product.setStock(100);
        product.setDescription("Description");
        product.setImageUrl("ImageUrl");

        Product product2 = new Product();
        product2.setProductId(3);
        product2.setProductName("ProductName");
        product2.setCategory(ProductCategory.FOOD);
        product2.setPrice(100);
        product2.setStock(100);
        product2.setDescription("Description");
        product2.setImageUrl("ImageUrl");

        List<Product> exceptProductList = List.of(product,product2);

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setSearch("ProductName");
        productQueryParams.setCategory(ProductCategory.FOOD);

        Sort sort = Sort.by(Sort.Direction.ASC, "productId");

        Mockito.when(productRepository.findAll(Mockito.any(Specification.class), Mockito.eq(sort))).thenReturn(exceptProductList);

        // [Act] 執行測試
        List<Product> result = productService.getProductListByCondition(productQueryParams);

        // [Assert] 驗證結果
        assertEquals("testGetProductListByCondition",exceptProductList, result);
    }

    @Test
    public void testCreateProduct() {
        // [Arrange] 預期資料
        Product exceptProduct = new Product();
        Date now = new Date();
        exceptProduct.setProductName("ProductName");
        exceptProduct.setCategory(ProductCategory.FOOD);
        exceptProduct.setImageUrl("ImageUrl");
        exceptProduct.setPrice(100);
        exceptProduct.setStock(100);
        exceptProduct.setDescription("test");
        exceptProduct.setCreateDate(now);
        exceptProduct.setLastModifiedDate(now);


        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(exceptProduct);


        // [Act] 執行測試
        ProductRequest productRequest = new ProductRequest();
        Product result = productService.createProduct(productRequest);

        // [Assert] 驗證結果
        assertEquals("testGetProductList",exceptProduct, result);
    }

    @Test
    public void testUpdateProduct() {
        // [Arrange] 預期資料
        Product exceptProduct = new Product();
        Date now = new Date();
        exceptProduct.setProductName("ProductName");
        exceptProduct.setCategory(ProductCategory.FOOD);
        exceptProduct.setImageUrl("ImageUrl");
        exceptProduct.setPrice(100);
        exceptProduct.setStock(100);
        exceptProduct.setDescription("test");
        exceptProduct.setCreateDate(now);
        exceptProduct.setLastModifiedDate(now);


        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(exceptProduct);


        // [Act] 執行測試
        ProductRequest productRequest = new ProductRequest();
        Product result = productService.updateProduct(1,productRequest);

        // [Assert] 驗證結果
        assertEquals("testUpdateProduct",exceptProduct, result);
    }
}
