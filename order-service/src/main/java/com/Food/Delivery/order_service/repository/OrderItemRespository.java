package com.Food.Delivery.order_service.repository;

import com.Food.Delivery.order_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRespository extends JpaRepository<OrderItem ,Long> {

    List<OrderItem> findByOrderId(Long orderId);

}
