package com.Food.Delivery.order_service.client;

import com.Food.Delivery.order_service.dto.ApiResponse;
import com.Food.Delivery.order_service.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("restaurant-service")
public interface RestaurantClient {

    @GetMapping("/internal/{id}")
    public ApiResponse<RestaurantResponse> findByResId(@PathVariable Long id) ;

}
