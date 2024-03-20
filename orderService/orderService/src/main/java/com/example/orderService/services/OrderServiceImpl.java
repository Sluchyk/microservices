package com.example.orderService.services;

import com.example.orderService.client.PaymentService;
import com.example.orderService.client.ProductService;
import com.example.orderService.entity.OrderEntity;
import com.example.orderService.entity.OrderRepository;
import com.example.orderService.entity.OrderStatus;
import com.example.orderService.exceptions.OrderNotFoundException;
import com.example.orderService.model.OrderRequest;
import com.example.orderService.model.OrderResponse;
import com.example.orderService.model.PaymentRequest;
import com.example.orderService.model.PaymentResponse;
import com.example.orderService.model.PaymentType;
import com.example.orderService.model.ProductResponse;
import java.math.BigDecimal;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final PaymentService paymentService;

    @Override
    public OrderResponse createNewOrder(OrderRequest productRequest) {
      BigDecimal price = productService.reduceQuantity(productRequest.getId(),
              productRequest.getQuantity()).getBody().getPrice();
        OrderEntity orderEntity = OrderEntity.builder()
                .quantity(productRequest.getQuantity())
                .orderStatus(OrderStatus.CREATED)
                .orderDate(new Date(System.currentTimeMillis()))
                .orderPrice(BigDecimal.valueOf(productRequest.getQuantity() * price.longValue()))
                .productId(productRequest.getId())
                .build();
        PaymentRequest request = PaymentRequest.builder()
                .orderId(productRequest.getId())
                .paymentType(PaymentType.CREDIT)
                .amount(orderEntity.getOrderPrice())
                .referencedNumber("546657574546")
                .build();
        OrderStatus orderStatus;
        try {
            paymentService.doPayment(request);
            orderStatus = OrderStatus.PAID;
        } catch (Exception e) {
            orderStatus = OrderStatus.FAILED;
        }
        orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);
        return OrderResponse.builder()
                .quantity(productRequest.getQuantity())
                .orderPrice(orderEntity.getOrderPrice())
                .orderDate(orderEntity.getOrderDate())
                .orderStatus(orderEntity.getOrderStatus())
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public OrderResponse getOrderById(long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Can`t find order with id: " + id));
        ProductResponse productResponse = productService.getProduct(orderEntity.getProductId()).getBody();
        PaymentResponse paymentResponse = paymentService.getPayment(orderEntity.getOrderId()).getBody();
       return OrderResponse.builder()
                .orderId(orderEntity.getOrderId())
                .orderPrice(orderEntity.getOrderPrice())
                .httpStatus(HttpStatus.FOUND)
                .paymentResponse(paymentResponse)
                .orderDate(orderEntity.getOrderDate())
                .quantity(orderEntity.getQuantity())
                .orderStatus(orderEntity.getOrderStatus())
                .productResponse(productResponse)
                .build();
    }
}
