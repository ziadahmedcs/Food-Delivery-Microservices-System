package com.Food.Delivery.order_service.repository;

import com.Food.Delivery.order_service.entity.Order;
import com.Food.Delivery.order_service.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespoistory extends JpaRepository<Order,Long> {

    List<Order> findByRestaurantId(Long restaurantId);
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(OrderStatus status);


}
