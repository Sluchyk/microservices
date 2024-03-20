package com.example.paymentService.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private long paymentId;
    private PaymentStatus status;
    private PaymentType paymentType;
    private Date paymentDate;
    private long orderId;
    private BigDecimal amount;

}
