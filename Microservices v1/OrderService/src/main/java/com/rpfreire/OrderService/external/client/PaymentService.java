package com.rpfreire.OrderService.external.client;

import com.rpfreire.OrderService.exception.CustomException;
import com.rpfreire.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PaymentService/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(
            @RequestBody PaymentRequest paymentRequest );

    default void fallback(Exception e){
        throw new CustomException("Payment service is not available ", "UNAVAILABLE",500);
    }


}
