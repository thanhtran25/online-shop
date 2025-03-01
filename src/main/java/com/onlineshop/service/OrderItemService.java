package com.onlineshop.service;

import com.onlineshop.dto.request.OrderItemUpdateRequest;
import com.onlineshop.dto.response.OrderItemResponse;
import com.onlineshop.entity.*;
import com.onlineshop.mapper.OrderItemMapper;
import com.onlineshop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemResponse getOrderItem(String orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toOrderItemResponse)
                .collect(Collectors.toList());
    }

    public OrderItemResponse updateOrderItem(String orderItemId, OrderItemUpdateRequest request) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        orderItemMapper.updateOrderItem(orderItem, request);
        return orderItemMapper.toOrderItemResponse(orderItemRepository.save(orderItem));
    }

    public void deleteOrderItem(String orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order item not found"));
        orderItemRepository.delete(orderItem);
    }
}
