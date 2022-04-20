package com.learning.msa.order.controller;

import com.learning.msa.order.dto.OrderDTO;
import com.learning.msa.order.entity.Order;
import com.learning.msa.order.repository.OrderRepository;
import com.learning.msa.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/orders")
@EnableCircuitBreaker
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @Resource
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody final OrderDTO order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Optional<Order> findOrderById(@PathVariable final Long id) {
        return repo.findById(id);
    }

    @PostMapping("/{id}/assignOrder")
    public String assignOrder(@PathVariable final Long id) {
        return orderService.assignOrder(id);

    }

    @PostMapping("/{id}/updateOrder")
    public Order updateOrder(@PathVariable final Long id, @RequestParam final String providerDecision) {
        return orderService.updateOrderStatus(id, providerDecision);
    }

}
