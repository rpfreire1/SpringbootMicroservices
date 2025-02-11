package com.rpfreire.OrderService.controller;

import com.rpfreire.OrderService.service.OrderService;
import com.rpfreire.OrderService.service.dto.req.OrderRequest;
import com.rpfreire.OrderService.service.dto.res.OrderResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody
                                                        OrderRequest orderRequest) {
        log.info("Placing order");
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        log.info("Order placed successfully "+ orderResponse);
        return ResponseEntity.ok(orderResponse);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(
            @PathVariable Long orderId){
        log.info("Fetching order details for order id: {}", orderId);
        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);

    }

}
