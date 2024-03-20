package com.example.orderService.services;

import com.example.orderService.model.OrderRequest;
import com.example.orderService.model.OrderResponse;

public interface OrderService {
    OrderResponse createNewOrder(OrderRequest productRequest);

    OrderResponse getOrderById(long id);
}
