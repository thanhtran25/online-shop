package com.onlineshop.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreationRequest {
    @NotBlank(message = "User ID  is required")
    private String userId;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero")
    private BigDecimal totalPrice;

    @NotBlank(message = "Status is required")
    private String status;

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderItemCreationRequest> orderItems;
}
