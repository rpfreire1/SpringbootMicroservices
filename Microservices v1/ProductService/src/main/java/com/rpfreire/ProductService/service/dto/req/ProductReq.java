package com.rpfreire.ProductService.service.dto.req;

import com.rpfreire.ProductService.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProductReq implements Serializable {
    private String name;
    private BigDecimal price;
    private Long quantity;




}
