package com.rpfreire.PaymentService.controller;

import com.rpfreire.PaymentService.dto.req.PaymentRequest;
import com.rpfreire.PaymentService.dto.res.PaymentResponse;
import com.rpfreire.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(
            @RequestBody PaymentRequest paymentRequest
            ){
        return ResponseEntity.ok(this.paymentService.doPayment(paymentRequest));
    }
    @GetMapping("/getPaymentDetails/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable Long orderId){
        return ResponseEntity.ok(this.paymentService.getPaymentDetailsByOrderId(orderId));
    }
}
