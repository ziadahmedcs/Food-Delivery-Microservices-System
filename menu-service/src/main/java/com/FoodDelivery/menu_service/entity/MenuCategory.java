package com.FoodDelivery.menu_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "menu_category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(nullable = false )
    private  String name ;
    @Column(nullable = false)
    private Long restaurantId ;
    @CreationTimestamp
    private LocalDateTime createdAt ;
    @UpdateTimestamp
    private  LocalDateTime updatedAt ;
}
