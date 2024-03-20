package com.example.orderService.web;

import com.example.orderService.model.OrderRequest;
import com.example.orderService.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/newOrder")
    public ResponseEntity<?> createNewOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createNewOrder(orderRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    
}
