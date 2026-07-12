package com.Food.Delivery.restaurant_service.controller;

import com.Food.Delivery.restaurant_service.dto.ApiResponse;
import com.Food.Delivery.restaurant_service.dto.RestaurantRequest;
import com.Food.Delivery.restaurant_service.dto.RestaurantResponse;
import com.Food.Delivery.restaurant_service.dto.UpdateRestaurantRequest;
import com.Food.Delivery.restaurant_service.entity.Restaurant;
import com.Food.Delivery.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class RestaurantInternalController {
    private final RestaurantService restaurantService;


    @GetMapping("/{id}")
    public ApiResponse<RestaurantResponse> findByResId(@PathVariable Long id)
    {
        return restaurantService.findResById(id) ;
    }

}
