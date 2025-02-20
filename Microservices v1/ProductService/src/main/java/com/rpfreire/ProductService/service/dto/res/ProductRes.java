package com.rpfreire.ProductService.service.dto.res;

import com.rpfreire.ProductService.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRes implements Serializable {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Long quantity;


}
