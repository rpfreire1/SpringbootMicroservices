package com.rpfreire.OrderService.service;

import com.rpfreire.OrderService.service.dto.req.OrderRequest;
import com.rpfreire.OrderService.service.dto.res.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
