package com.onlineshop.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshop.entity.Order;
import com.onlineshop.entity.OrderItem;
import com.onlineshop.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String userId;
    BigDecimal totalPrice;
    String status;
    Set<OrderItemResponse> orderItems;
}
