package com.Food.Delivery.restaurant_service.dto;

import com.Food.Delivery.restaurant_service.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
