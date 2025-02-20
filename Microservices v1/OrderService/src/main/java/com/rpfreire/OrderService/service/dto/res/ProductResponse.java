package com.rpfreire.OrderService.service.dto.res;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Long quantity;
}
