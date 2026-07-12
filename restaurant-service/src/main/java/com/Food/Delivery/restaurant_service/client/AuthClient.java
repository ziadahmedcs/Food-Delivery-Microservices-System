package com.Food.Delivery.restaurant_service.client;

import com.Food.Delivery.restaurant_service.dto.ApiResponse;
import com.Food.Delivery.restaurant_service.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/auth/users/{id}")
    ApiResponse<UserResponse> getUserById(@PathVariable Long id);

}