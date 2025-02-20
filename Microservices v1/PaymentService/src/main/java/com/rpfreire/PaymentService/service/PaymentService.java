package com.rpfreire.PaymentService.service;

import com.rpfreire.PaymentService.dto.req.PaymentRequest;
import com.rpfreire.PaymentService.dto.res.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
