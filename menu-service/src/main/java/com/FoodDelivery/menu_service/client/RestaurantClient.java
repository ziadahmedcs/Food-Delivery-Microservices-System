package com.FoodDelivery.menu_service.client;

import com.FoodDelivery.menu_service.dto.ApiResponse;
import com.FoodDelivery.menu_service.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service")
public interface RestaurantClient {
    @GetMapping("/internal/{id}")
    public ApiResponse<RestaurantResponse> findByResId(@PathVariable Long id) ;
}
