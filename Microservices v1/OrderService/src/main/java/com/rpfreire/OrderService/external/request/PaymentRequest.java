package com.rpfreire.OrderService.external.request;


import com.rpfreire.OrderService.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long orderId;
     private Double amount;
     private String referenceNumber;
     private PaymentMode paymentMethod;
}
