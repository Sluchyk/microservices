package com.example.paymentService.web;

import com.example.paymentService.model.PaymentRequest;
import com.example.paymentService.model.PaymentResponse;
import com.example.paymentService.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest request){
        PaymentResponse paymentResponse = paymentService.doPayment(request);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable long id){
       PaymentResponse paymentResponse = paymentService.getPaymentByOrderId(id);
        return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
    }
}
