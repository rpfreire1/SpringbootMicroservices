package com.rpfreire.OrderService.external.response;

import com.rpfreire.OrderService.enums.PaymentMode;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private String status;
    private PaymentMode paymentMode;
    private Double amount;
    private Instant paymentDate;

}
