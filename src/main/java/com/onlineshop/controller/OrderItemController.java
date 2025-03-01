package com.onlineshop.controller;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.ApiResponse;
import com.onlineshop.dto.response.OrderItemResponse;
import com.onlineshop.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public ApiResponse<List<OrderItemResponse>> getOrderItems() {
        return ApiResponse.<List<OrderItemResponse>>builder()
                .result(orderItemService.getOrderItems())
                .build();
    }

    @GetMapping("/{orderItemId}")
    public ApiResponse<OrderItemResponse> getOrderItem(@PathVariable String orderItemId) {
        return ApiResponse.<OrderItemResponse>builder()
                .result(orderItemService.getOrderItem(orderItemId))
                .build();
    }

    @PutMapping("/{orderItemId}")
    public ApiResponse<OrderItemResponse> updateOrderItem(@PathVariable String orderItemId, @RequestBody @Valid OrderItemUpdateRequest request) {
        return ApiResponse.<OrderItemResponse>builder()
                .result(orderItemService.updateOrderItem(orderItemId, request))
                .build();
    }

    @DeleteMapping("/{orderItemId}")
    public ApiResponse<Void> deleteOrderItem(@PathVariable String orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
