package com.example.orderService.client;

import com.example.orderService.client.decoder.ErrorResponse;
import com.example.orderService.exceptions.CustomException;
import com.example.orderService.model.ProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "ProductService",path = "/api/v1/products")
public interface ProductService {
    @CircuitBreaker(name = "external",fallbackMethod = "callFallback")
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<ProductResponse> reduceQuantity(@PathVariable("id") long id,
                                                   @RequestParam int quantity);
    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProduct(@PathVariable("id") long productId);
    default ResponseEntity<Void> callFallback(Exception e) {
        throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
