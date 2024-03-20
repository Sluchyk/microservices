package com.example.productService.service;

import com.example.productService.entity.ProductEntity;
import com.example.productService.exceptions.ProductCustomException;
import com.example.productService.exceptions.ProductNotFoundException;
import com.example.productService.model.ProductRequest;
import com.example.productService.model.ProductResponse;
import com.example.productService.repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
    private final ProductRepository productRepository;
    @Override
    public ProductResponse addNewProduct(ProductRequest productRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .build();
       var saved = productRepository.save(productEntity);
        logger.info("saved entity" + saved);
        return ProductResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
    }

    @Override
    public ProductResponse getProduct(long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Can`t find product with id: "+ productId));
        logger.info(productEntity.toString());
        return ProductResponse.builder()
                .name(productEntity.getName())
                .httpStatus(HttpStatus.FOUND)
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .build();
    }

    @Override
    public ProductResponse reduceQuantity(long id, int quantity) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Can`t find product with id: "+id));
        logger.info(productEntity.toString());
        if(productEntity.getQuantity() < quantity){
            throw new ProductCustomException("Insufficient quantity");
        }
        productEntity.setQuantity(productEntity.getQuantity()-quantity);
        productRepository.save(productEntity);
        return ProductResponse.builder()
                .price(productEntity.getPrice())
                .build();
    }
}
