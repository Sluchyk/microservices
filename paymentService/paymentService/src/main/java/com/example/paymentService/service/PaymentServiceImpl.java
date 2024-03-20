package com.example.paymentService.service;

import com.example.paymentService.exceptions.PaymentNotFoundException;
import com.example.paymentService.model.PaymentRequest;
import com.example.paymentService.model.PaymentResponse;
import com.example.paymentService.model.PaymentStatus;
import com.example.paymentService.repository.PaymentEntity;
import com.example.paymentService.repository.PaymentRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    @Override
    public PaymentResponse doPayment(PaymentRequest request) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .paymentDate(new Date(System.currentTimeMillis()))
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .paymentType(request.getPaymentType())
                .paymentStatus(PaymentStatus.SUCCESS)
                .referenceNumber(request.getReferencedNumber())
                .build();
        repository.save(paymentEntity);
        return PaymentResponse.builder()
                .paymentId(paymentEntity.getId())
                .paymentType(paymentEntity.getPaymentType())
                .paymentDate(paymentEntity.getPaymentDate())
                .orderId(paymentEntity.getOrderId())
                .status(paymentEntity.getPaymentStatus())
                .build();
    }
    @Override
    public PaymentResponse getPaymentByOrderId(long id) {
        PaymentEntity paymentEntity = repository.findPaymentEntityByOrderId(id)
                .orElseThrow(()-> new PaymentNotFoundException("Can`t find payment for order with id: "+id));
        System.out.println(paymentEntity.toString());
        return PaymentResponse.builder()
                .paymentId(paymentEntity.getId())
                .status(paymentEntity.getPaymentStatus())
                .orderId(paymentEntity.getOrderId())
                .paymentDate(paymentEntity.getPaymentDate())
                .paymentType(paymentEntity.getPaymentType())
                .amount(paymentEntity.getAmount())
                .build();
    }

}
