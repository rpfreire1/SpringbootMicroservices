package com.rpfreire.OrderService.service.dto.req;

import com.rpfreire.OrderService.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
        private Long productId;
        private Long quantity;
        private Double amount;
        private PaymentMode paymentMode;

}
