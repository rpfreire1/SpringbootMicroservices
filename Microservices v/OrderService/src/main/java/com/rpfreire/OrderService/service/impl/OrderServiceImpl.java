package com.rpfreire.OrderService.service.impl;

import com.rpfreire.OrderService.entity.Order;
import com.rpfreire.OrderService.repository.OrderRepository;
import com.rpfreire.OrderService.service.OrderService;
import com.rpfreire.OrderService.service.dto.req.OrderRequest;
import com.rpfreire.OrderService.service.dto.res.OrderResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //TODO: Order entitty creation and save with status order CREATED
        //TODO: Product service to blockProducts(Reduce stock)
        //TODO:  Payment service to processPayment Payment complete
        //TODO OR ELSE CANCELLED
        log.info("Placing order Request: {}", orderRequest);
        Order order=Order.builder()
                .ammount(orderRequest.getAmmount())
                .status("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepository.save(order);
        log.info("Order placed successfully: {}", order);

        return OrderResponse.builder()
                .orderId(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .ammount(order.getAmmount())
                .build();
    }
}
