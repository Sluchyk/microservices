package com.example.orderService.model;

import com.example.orderService.entity.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class  Order {
    private long orderId;
    private int quantity;
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal orderPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getQuantity() == order.getQuantity() && getOrderDate().equals(order.getOrderDate()) && getOrderStatus() == order.getOrderStatus() && getOrderPrice().equals(order.getOrderPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getOrderDate(), getOrderStatus(), getOrderPrice());
    }
}
