package com.onlineshop.mapper;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.OrderItemResponse;
import com.onlineshop.dto.response.UserResponse;
import com.onlineshop.entity.*;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "order", source = "order")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", expression = "java(calculatePrice(product, request.getQuantity()))")
    OrderItem toOrderItem (OrderItemCreationRequest request, Order order, Product product);

    OrderItemResponse toOrderItemResponse (OrderItem orderItem);


    void updateOrderItem (@MappingTarget OrderItem orderItem, OrderItemUpdateRequest request);
    default BigDecimal calculatePrice(Product product, int quantity) {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
