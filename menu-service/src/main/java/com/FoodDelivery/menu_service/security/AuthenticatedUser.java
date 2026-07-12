package com.FoodDelivery.menu_service.security;

import com.FoodDelivery.menu_service.enums.UserRole;
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