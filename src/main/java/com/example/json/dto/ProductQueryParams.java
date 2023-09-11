package com.example.json.dto;

import com.example.json.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryParams {

    ProductCategory category;
    String search;
}
