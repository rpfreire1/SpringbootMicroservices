package com.rpfreire.ProductService.service;

import com.rpfreire.ProductService.service.dto.req.ProductReq;
import com.rpfreire.ProductService.service.dto.res.ProductRes;

public interface ProductService {
    ProductRes addProduct(ProductReq productReq);

    ProductRes getProductById(Long id);
}
