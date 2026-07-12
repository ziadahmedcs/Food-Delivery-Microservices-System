package com.Food.Delivery.restaurant_service.repository;

import com.Food.Delivery.restaurant_service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestuarantRepository extends JpaRepository<Restaurant , Long> {

    boolean existsByName(String name);

    boolean existsByPhone(String phone);

    boolean existsByOwnerId(Long ownerId);

    Optional<Restaurant> findByOwnerId(Long ownerId);
}
