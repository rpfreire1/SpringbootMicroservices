package com.rpfreire.OrderService.service.dto.res;

import com.rpfreire.OrderService.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private Instant orderDate;
    private String orderStatus;
    private Double amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    public static class ProductDetails {
        private Integer id;
        private String name;
        private BigDecimal price;
        private Long quantity;


    }
    @Data
    @Builder
    public static class PaymentDetails {
        private Long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;


    }


}
