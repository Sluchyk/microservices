package com.example.orderService.client.config;

import com.example.orderService.client.decoder.CustomErrorDecoder;
import feign.Feign;
import feign.Target;
import feign.codec.ErrorDecoder;
import java.lang.reflect.Method;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
