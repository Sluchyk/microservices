package com.example.orderService.client.decoder;

import com.example.orderService.exceptions.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.util.logging.Logger;

public class CustomErrorDecoder implements ErrorDecoder {
    private static final Logger log = Logger.getLogger(CustomErrorDecoder.class.getName());
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal service error",500);
        }

    }
}
