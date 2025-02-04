package com.rpfreire.ProductService.controller;

import com.rpfreire.ProductService.service.ProductService;
import com.rpfreire.ProductService.service.dto.req.ProductReq;
import com.rpfreire.ProductService.service.dto.res.ProductRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ProductRes> addProduct(
            @RequestBody ProductReq productReq
    ){
        return ResponseEntity.ok(this.productService.addProduct(productReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> getProduct(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.productService.getProductById(id));
    }


}
