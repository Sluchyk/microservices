package com.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class OrderResponse extends Order {
    private HttpStatus httpStatus;
    private PaymentResponse paymentResponse;
    private ProductResponse productResponse;
}
