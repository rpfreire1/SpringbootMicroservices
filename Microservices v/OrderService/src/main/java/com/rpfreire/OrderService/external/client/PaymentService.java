package com.rpfreire.OrderService.external.client;

import com.rpfreire.OrderService.external.request.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PaymentService/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(
            @RequestBody PaymentRequest paymentRequest );
}
