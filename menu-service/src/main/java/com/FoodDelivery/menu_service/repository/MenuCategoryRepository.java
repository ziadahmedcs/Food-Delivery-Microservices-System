package com.FoodDelivery.menu_service.repository;

import com.FoodDelivery.menu_service.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuCategoryRepository  extends JpaRepository<MenuCategory ,Long> {
    boolean existsByNameAndRestaurantId(String name, Long restaurantId);

    List<MenuCategory> findByRestaurantId(Long restaurantId);
}
