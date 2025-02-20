package com.rpfreire.PaymentService.dto.req;

import com.rpfreire.PaymentService.enums.PaymentMode;
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
