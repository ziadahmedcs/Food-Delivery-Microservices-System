package com.Food.Delivery.auth_service.entity;

import com.Food.Delivery.auth_service.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    @Column(nullable = false )
    private String name ;
    @Column(nullable = false , unique = true)
    private  String email ;
    @Column(nullable = false)
    private  String password ;
    @Column(nullable = false ,unique = true)
    private  String  phone ;
    @Enumerated(EnumType.STRING)
    private UserRole userRole ;
    @Builder.Default
    @Column(nullable = false)
    private boolean enabled = true;
    @CreationTimestamp
    @Column(name ="createdAt" )
    private LocalDateTime createdAt ;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    private  LocalDateTime updatedAt ;
}
