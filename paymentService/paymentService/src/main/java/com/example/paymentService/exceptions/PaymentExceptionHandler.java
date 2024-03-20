package com.example.paymentService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(PaymentNotFoundException e){
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
