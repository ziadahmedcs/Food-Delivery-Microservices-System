package com.FoodDelivery.menu_service.repository;

import com.FoodDelivery.menu_service.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem ,Long> {
    boolean existsByNameAndCategoryId(String name, Long categoryId);

    List<MenuItem> findByCategoryId(Long categoryId);

    List<MenuItem> findByRestaurantId(Long restaurantId);

    Optional<MenuItem> findById(Long id);

    boolean existsByNameAndRestaurantId(String name, Long restaurantId);
}

