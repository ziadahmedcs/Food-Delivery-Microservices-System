package com.FoodDelivery.menu_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Long id ;
    @Column(nullable = false)
    private  String name ;
    private  String description ;
    @Column(nullable = false)
    private BigDecimal price ;
     private  String imageUrl ;
     private  boolean available ;
    @Column(nullable = false)
    private Long restaurantId ;
    @Column(nullable = false)
    private  Long categoryId ;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
