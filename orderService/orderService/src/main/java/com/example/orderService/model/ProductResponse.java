package com.example.orderService.model;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private HttpStatus httpStatus;
}
