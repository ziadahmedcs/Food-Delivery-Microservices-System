package com.Food.Delivery.restaurant_service.dto;

import com.Food.Delivery.restaurant_service.enums.UserRole;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private  Long id ;
    private  String name ;
    private  String email ;
    private  String phone ;
    private UserRole role ;
}
