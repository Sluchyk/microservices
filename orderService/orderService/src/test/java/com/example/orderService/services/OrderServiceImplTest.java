package com.example.orderService.services;

import com.example.orderService.client.PaymentService;
import com.example.orderService.client.ProductService;
import com.example.orderService.entity.OrderEntity;
import com.example.orderService.entity.OrderRepository;
import com.example.orderService.entity.OrderStatus;
import com.example.orderService.model.OrderResponse;
import com.example.orderService.model.PaymentRequest;
import com.example.orderService.model.PaymentResponse;
import com.example.orderService.model.PaymentStatus;
import com.example.orderService.model.PaymentType;
import com.example.orderService.model.ProductResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductService productService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private OrderServiceImpl orderService;
    private final PaymentResponse paymentResponse = new PaymentResponse(1L, PaymentStatus.SUCCESS, PaymentType.CREDIT, new Date(System.currentTimeMillis()), 1L, BigDecimal.valueOf(300));
    private final PaymentRequest paymentRequest = new PaymentRequest(1L, BigDecimal.valueOf(300), "454647", PaymentType.CREDIT);
    private final ProductResponse productResponse = ProductResponse.builder().quantity(100).name("apple").price(BigDecimal.valueOf(100)).description("Green").build();
    private final OrderEntity orderEntity = OrderEntity.builder().orderId(1L).productId(1L).orderPrice(BigDecimal.valueOf(300))
            .orderDate(new Date(System.currentTimeMillis())).orderStatus(OrderStatus.CREATED).quantity(3).build();
    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        productService = mock(ProductService.class);
        paymentService = mock(PaymentService.class);
        orderService = new OrderServiceImpl(orderRepository, productService, paymentService);
    }
    @DisplayName("GET ORDER BY ID")
    @Test
    public void test_getOrderById(){
        when(orderRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderEntity));
        when(productService.getProduct(orderEntity.getProductId())).thenReturn(ResponseEntity.ok(productResponse));
        when(paymentService.getPayment(orderEntity.getOrderId())).thenReturn(ResponseEntity.ok(paymentResponse));
        OrderResponse order1 = orderService.getOrderById(1);
        verify(orderRepository,times(1)).findById(anyLong());
        verify(productService,times(1)).getProduct(orderEntity.getProductId());
        verify(paymentService,times(1)).getPayment(orderEntity.getOrderId());

        assertNotNull(order1);
        assertEquals(orderEntity.getOrderId(), order1.getOrderId());
        assertEquals(orderEntity.getOrderDate(),order1.getOrderDate());
        assertEquals(orderEntity.getOrderPrice(),order1.getOrderPrice());
        assertEquals(orderEntity.getOrderStatus(),order1.getOrderStatus());
    }
}