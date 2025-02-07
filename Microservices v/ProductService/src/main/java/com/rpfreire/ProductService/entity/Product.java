package com.rpfreire.ProductService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code")
    private Integer id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private BigDecimal price;
    @Column(name = "product_quantity")
    private Long quantity;

}
