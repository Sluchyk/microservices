package com.example.productService.web;

import com.example.productService.model.ProductRequest;
import com.example.productService.model.ProductResponse;
import com.example.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Validated ProductRequest productRequest) {
       return ResponseEntity.ok(productService.addNewProduct(productRequest));
    }
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "id") long productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }
    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<ProductResponse> reduceQuantity(@PathVariable("id") long id,
                                               @RequestParam int quantity){
        return ResponseEntity.ok(productService.reduceQuantity(id,quantity));
    }
}
