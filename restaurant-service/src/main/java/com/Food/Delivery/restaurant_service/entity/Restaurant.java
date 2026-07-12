package com.Food.Delivery.restaurant_service.entity;

import com.Food.Delivery.restaurant_service.dto.RestaurantResponse;
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

@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column (unique = true , nullable = false)
    private  String name ;
    private  String description ;
    @Column(nullable = false)
    private  String address ;
    @Column(nullable = false, unique = true)
    private  String phone ;
    private  Long ownerId ;
    @Enumerated(EnumType.STRING)
    private RestaurantStatus restaurantStatus ;
    @CreationTimestamp
    private LocalDateTime createdAt ;
    @UpdateTimestamp
    private  LocalDateTime updatedAt ;


}
