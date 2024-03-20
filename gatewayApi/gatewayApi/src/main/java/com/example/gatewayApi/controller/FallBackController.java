package com.example.gatewayApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @GetMapping("/orderServiceFallBack")
    public String orderServiceFallBack(){
        return "Something was going wrong with order service. Try later!!!";
    }
    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack(){
        return "Something was going wrong with product service. Try later!!!";
    }
    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack(){
        return "Something was going wrong with payment. Try later!!!";
    }
}
