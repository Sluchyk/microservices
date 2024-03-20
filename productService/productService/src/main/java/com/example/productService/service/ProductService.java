package com.example.productService.service;

import com.example.productService.model.ProductRequest;
import com.example.productService.model.ProductResponse;
import org.apache.coyote.Response;

public interface ProductService {
    ProductResponse addNewProduct(ProductRequest productRequest);

    ProductResponse getProduct(long productId);

    ProductResponse reduceQuantity(long id, int quantity);
}
