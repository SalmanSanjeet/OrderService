package com.shop.OrderService.service;

import com.shop.OrderService.entity.Order;
import com.shop.OrderService.model.OrderRequest;
import com.shop.OrderService.repository.OrderRepository;
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
    public long placeOrder(OrderRequest orderRequest) {

        log.info("placing order request : {}",orderRequest);

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .build();

        order =  orderRepository.save(order);

        log.info("Order place succefully with order id {}",order.getId() );
        return order.getId();
    }
}
