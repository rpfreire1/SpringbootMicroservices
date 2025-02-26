package com.rpfreire.OrderService.service.impl;

import com.rpfreire.OrderService.entity.Order;
import com.rpfreire.OrderService.exception.CustomException;
import com.rpfreire.OrderService.external.client.PaymentService;
import com.rpfreire.OrderService.external.client.ProductService;
import com.rpfreire.OrderService.external.request.PaymentRequest;
import com.rpfreire.OrderService.external.response.PaymentResponse;
import com.rpfreire.OrderService.repository.OrderRepository;
import com.rpfreire.OrderService.service.OrderService;
import com.rpfreire.OrderService.service.dto.req.OrderRequest;
import com.rpfreire.OrderService.service.dto.res.OrderResponse;
import com.rpfreire.OrderService.service.dto.res.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        //TODO: Order entitty creation and save with status order CREATED
        //TODO: Product service to blockProducts(Reduce stock)
        //TODO:  Payment service to processPayment Payment complete
        //TODO OR ELSE CANCELLED
        log.info("Placing order Request: {}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating order with status CREATED");

        Order order=Order.builder()
                .amount(orderRequest.getAmount())
                .status("CREATED")
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getAmount())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        log.info("Order placed successfully: {}", order);
        order=orderRepository.save(order);
        log.info("Processing payment for order: {}", order);
        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMethod(orderRequest.getPaymentMode())
                .amount(order.getAmount())
                .build();
        String orderStatus=null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment processed successfully for order: {}", order);
            orderStatus="COMPLETED";
        }catch (Exception e){
            log.error("Error while processing payment for order: {}", order);
            log.error("Cancelling order: {}", order);
            orderStatus="CANCELLED";
        }
        order.setStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order placed successfully: {}", order);
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .amount(order.getAmount())
                .build();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {
        log.info("Fetching order details for order id: {}", orderId);
        Order order=orderRepository.findById(orderId).orElseThrow( ()-> new CustomException("Order not found","NOT_FOUND",404));
       log.info("Fetching product details for order id: {}", orderId);
       ProductResponse productRes=restTemplate.getForObject
               ("http://ProductService/product/"+order.getProductId(), ProductResponse.class);
        log.info("Getting paymentInfo from the payment service");
        PaymentResponse paymentResponse=restTemplate.getForObject
                ("http://PaymentService/payment/getPaymentDetails/"+order.getProductId(), PaymentResponse.class);
        OrderResponse.PaymentDetails paymentDetails=OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .build();

        OrderResponse.ProductDetails productDetails=OrderResponse.ProductDetails.builder()
                .id(productRes.getId())
                .name(productRes.getName())
                .price(productRes.getPrice())
                .quantity(productRes.getQuantity())
                .build();

        OrderResponse orderResponse=OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus())
                .orderDate(order.getOrderDate())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .build();
        log.info("Order details fetched successfully: {}", orderResponse);
        return orderResponse;

    }
}
