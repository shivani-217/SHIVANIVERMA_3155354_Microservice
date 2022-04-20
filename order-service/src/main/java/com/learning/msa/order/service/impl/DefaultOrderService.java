package com.learning.msa.order.service.impl;

import com.learning.msa.order.dto.Consumer;
import com.learning.msa.order.dto.OrderDTO;
import com.learning.msa.order.dto.Professional;
import com.learning.msa.order.dto.ServiceProvider;
import com.learning.msa.order.entity.Order;
import com.learning.msa.order.repository.OrderRepository;
import com.learning.msa.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    RabbitMQSender rabbitMQSender;


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    @HystrixCommand(fallbackMethod = "handleErrorCase")
    public String createOrder(OrderDTO orderDTO) {
        String customerEmail = orderDTO.getEmail();
        String baseUrl = loadBalancerClient.choose("consumer-service").getUri().toString() + "/api/consumer/";
        Consumer consumer = restTemplate().getForObject(baseUrl + customerEmail, Consumer.class);

        Order order = new Order();
        order.setCustomerEmail(consumer.getEmail());
        order.setCustomerAddress(consumer.getAddress());
        order.setCreationDate(new Date());
        order.setServiceCode(orderDTO.getServiceCode());
        order.setOrderStatus("CREATED");
        order.setServicePrice(orderDTO.getServicePrice());
        orderRepository.save(order);
        return "Order Created Successfully with order Number :"+order.getId();
    }


    @Override
    public String assignOrder(Long orderCode) {

        Optional<Order> optional = orderRepository.findById(orderCode);
        if (optional.isPresent()) {
            Order order = optional.get();
            String baseUrl = loadBalancerClient.choose("provider-service").getUri().toString() + "/api/provider/";
            try {
                Professional professional = restTemplate().getForObject(baseUrl + order.getServiceCode(), Professional.class);
                int min = 1;
                int max = 5;
                List<ServiceProvider> list = professional.getServiceProviderList();
                Random rand = new Random();
                int randomNum = rand.nextInt((max - min) + 1) + min;
                ServiceProvider serviceProvider = list.get(randomNum);
                order.setServiceProviderName(serviceProvider.getProviderName());
                order.setServiceProviderId(serviceProvider.getId());
                order.setOrderStatus("ASSIGNED");
                orderRepository.save(order);
                rabbitMQSender.send("Notification Send to the Provider :" + serviceProvider.getId() + serviceProvider.getProviderName());
                System.out.println("Message Sent to RabbitMQ");
                return "Order :"+order.getId()+ "assigned to service provider "+order.getServiceProviderName();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
        return "Order can not be assigned to service provider";
    }

    public String handleErrorCase(OrderDTO orderDTO)
    {
        return "Order creation failed";
    }

    @Override
    public Order updateOrderStatus(Long orderCode, String providerStatus) {
        Optional<Order> optional = orderRepository.findById(orderCode);
        if (optional.isPresent()) {
            Order order = optional.get();
            order.setProviderStatus(providerStatus);
            if (providerStatus.equalsIgnoreCase("accept")) {
                order.setOrderStatus("PLACED");
                rabbitMQSender.send("Notification Sent to the Consumer" + order);
                rabbitMQSender.send("Notification Sent to the Provider" + order);
                orderRepository.save(order);
            }
            else
            {
                order.setOrderStatus("CANCELLED");
                orderRepository.save(order);
            }
            return order;
        }
        return null;
    }
}
