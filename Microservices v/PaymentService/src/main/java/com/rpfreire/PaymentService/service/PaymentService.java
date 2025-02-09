package com.rpfreire.PaymentService.service;

import com.rpfreire.PaymentService.dto.req.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
