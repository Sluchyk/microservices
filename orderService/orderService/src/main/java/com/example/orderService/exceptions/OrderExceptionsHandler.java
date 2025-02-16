package com.example.orderService.exceptions;

import com.example.orderService.client.decoder.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException){
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(customException.getMessage())
                .build(),
                HttpStatusCode.valueOf(customException.getStatus()));
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException){
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(orderNotFoundException.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
