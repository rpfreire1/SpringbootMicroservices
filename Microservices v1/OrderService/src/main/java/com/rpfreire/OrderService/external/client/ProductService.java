package com.rpfreire.OrderService.external.client;

import com.rpfreire.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "ProductService/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
     ResponseEntity<Void> reduceQuantity(
            @PathVariable Long id,
            @RequestParam Long quantity
    );
    default void fallback(Exception e){
        throw new CustomException("Product service is not available ", "UNAVAILABLE",500);
    }
}
