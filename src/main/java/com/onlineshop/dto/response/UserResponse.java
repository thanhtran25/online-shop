package com.onlineshop.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshop.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    String role;
    Set<OrderResponse> orders;
}
