package com.example.paymentService.service;

import com.example.paymentService.model.PaymentRequest;
import com.example.paymentService.model.PaymentResponse;
import com.example.paymentService.model.PaymentStatus;
import com.example.paymentService.repository.PaymentEntity;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    PaymentResponse doPayment(PaymentRequest request);

    PaymentResponse getPaymentByOrderId(long id);
}
