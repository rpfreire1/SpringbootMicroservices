package com.rpfreire.PaymentService.dto.res;

import com.rpfreire.PaymentService.enums.PaymentMode;
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
