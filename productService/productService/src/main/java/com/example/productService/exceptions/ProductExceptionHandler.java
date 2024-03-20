package com.example.productService.exceptions;

import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(ProductCustomException.class.getName());
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        LOGGER.info(productNotFoundException.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(productNotFoundException.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductCustomException(ProductCustomException e){
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
