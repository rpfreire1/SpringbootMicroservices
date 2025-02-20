package com.rpfreire.PaymentService.service.impl;

import com.rpfreire.PaymentService.dto.req.PaymentRequest;
import com.rpfreire.PaymentService.dto.res.PaymentResponse;
import com.rpfreire.PaymentService.entity.TransactionDetails;
import com.rpfreire.PaymentService.enums.PaymentMode;
import com.rpfreire.PaymentService.repository.TransactionDetailsRepository;
import com.rpfreire.PaymentService.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        System.out.println("entro");
        log.info("Payment request received for order id: {}", paymentRequest);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMethod(paymentRequest.getPaymentMethod().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        this.transactionDetailsRepository.save(transactionDetails);
         log.info("Payment done for order id: {}", transactionDetails);
        return null;
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Fetching payment details for order id: {}", orderId);
        TransactionDetails transactionDetails=this.transactionDetailsRepository.findByOrderId(orderId).orElseThrow(()->{
            log.error("No payment details found for order id: {}", orderId);
            return new RuntimeException("No payment details found for order id: "+orderId);
        });
        PaymentResponse paymentResponse=PaymentResponse.builder()
                .amount(transactionDetails.getAmount())
                .paymentDate(transactionDetails.getPaymentDate())
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMethod()))
                .status(transactionDetails.getPaymentStatus())
                .orderId(transactionDetails.getOrderId())
                .build();
        return paymentResponse;
    }
}
