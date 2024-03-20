package com.example.orderService.client;

import com.example.orderService.client.decoder.ErrorResponse;
import com.example.orderService.exceptions.CustomException;
import com.example.orderService.model.PaymentRequest;
import com.example.orderService.model.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external",fallbackMethod = "callFallback")
@FeignClient(name = "PaymentService",path = "/api/v1/payment")
public interface PaymentService {
    @PostMapping("/pay")
    ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest request);
    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> getPayment(@PathVariable("id") long id);
    default ResponseEntity<Void> callFallback(Exception e) {
        throw new CustomException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
