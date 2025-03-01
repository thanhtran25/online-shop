package com.onlineshop.mapper;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.OrderResponse;
import com.onlineshop.entity.*;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "orderItems", ignore = true)
    Order toOrder (OrderCreationRequest request);

    void updateOrder (@MappingTarget Order order, OrderUpdateRequest request);

    @Mapping(target = "id", ignore = true)
    void mappingUser (@MappingTarget Order order, User user);

    @Mapping(target = "userId", source = "user.id")
    OrderResponse toOrderResponse (Order order);
}
