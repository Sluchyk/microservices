package com.example.orderService.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private int status;
    public CustomException(String message,int status){
        super(message);
        this.status = status;
    }
}
