package com.learning.msa.order.service;

import com.learning.msa.order.entity.Order;
import com.learning.msa.order.dto.OrderDTO;

public interface OrderService {

    String createOrder(OrderDTO orderDTO);
    String assignOrder(Long orderCode);
    Order updateOrderStatus(Long orderCode,String providerStatus);
}
