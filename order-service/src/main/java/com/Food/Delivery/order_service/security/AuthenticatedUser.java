package com.Food.Delivery.order_service.security;

import com.Food.Delivery.restaurant_service.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUser {

    private Long id;

    private String email;

    private String phone;

    private UserRole role;
}