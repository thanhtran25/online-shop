package com.onlineshop.service;

import com.onlineshop.dto.request.OrderCreationRequest;
import com.onlineshop.dto.request.OrderUpdateRequest;
import com.onlineshop.dto.response.OrderItemResponse;
import com.onlineshop.dto.response.OrderResponse;
import com.onlineshop.entity.*;
import com.onlineshop.mapper.*;
import com.onlineshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    public OrderResponse createOrder(OrderCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderMapper.toOrder(request);
        orderMapper.mappingUser(order, user);

        List<String> productIds = request.getOrderItems().stream()
                .map(itemRequest -> itemRequest.getProductId())
                .collect(Collectors.toList());

        Map<String, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        Set<OrderItem> orderItems = request.getOrderItems().stream()
                .map(itemRequest -> orderItemMapper.toOrderItem(
                        itemRequest, order, productMap.get(itemRequest.getProductId())))
                .collect(Collectors.toSet());

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    Set<OrderItemResponse> orderItemResponseSetSet = order.getOrderItems().stream()
                            .map(orderItemMapper::toOrderItemResponse).collect(Collectors.toSet());
                    return orderMapper.toOrderResponse(order);
                })
                .collect(Collectors.toList());
    }

    public OrderResponse getOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse updateOrder(String orderId, OrderUpdateRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(request.getStatus());
        orderMapper.updateOrder(order, request);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
