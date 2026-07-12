package com.FoodDelivery.menu_service.dto;

import com.FoodDelivery.menu_service.enums.RestaurantStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {

    private  Long id ;
    private  String name ;
    private  String description ;
    private  String address ;
    private  String phone ;
    private  Long ownerId ;
    private RestaurantStatus restaurantStatus ;
}
