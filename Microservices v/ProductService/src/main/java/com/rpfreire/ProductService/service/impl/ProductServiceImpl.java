package com.rpfreire.ProductService.service.impl;

import com.rpfreire.ProductService.entity.Product;
import com.rpfreire.ProductService.exception.ProductServiceException;
import com.rpfreire.ProductService.repository.ProductRepository;
import com.rpfreire.ProductService.service.ProductService;
import com.rpfreire.ProductService.service.dto.req.ProductReq;
import com.rpfreire.ProductService.service.dto.res.ProductRes;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductRes addProduct(ProductReq productReq) {
        log.info("Product added");
        Product product = Product.builder()
                .productName(productReq.getName())
                .price(productReq.getPrice())
                .quantity(productReq.getQuantity())
                .build();
        this.productRepository.save(product);
        return ProductRes.builder()
                .id(product.getId())
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public ProductRes getProductById(Long id) {
        log.info("Getting product by id");
        Product product = this.productRepository.findById(id).orElseThrow(()->{
            log.error("Product not found");
            return new ProductServiceException("Product not found", "PRODUCT_NOT_FOUND");
        });
        return ProductRes.builder()
                .id(product.getId())
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

    }


}
