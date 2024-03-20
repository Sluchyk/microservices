package com.example.productService.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Please enter name of product, it can`t be empty")
    @Size(min = 2,message = "Name of product should be 2 or more symbols")
    private String name;
    @Min(value = 0,message = "Price of product can`t be negative")
    private BigDecimal price;
    @Min(value = 0,message = "Quantity of product can`t be negative")
    private int quantity;
    @NotBlank(message = "Please put some description,it can`t be empty")
    private String description;
}
