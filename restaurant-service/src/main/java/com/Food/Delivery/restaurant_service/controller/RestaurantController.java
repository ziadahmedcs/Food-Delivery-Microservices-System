package com.Food.Delivery.restaurant_service.controller;

import com.Food.Delivery.restaurant_service.client.AuthClient;
import com.Food.Delivery.restaurant_service.dto.*;
import com.Food.Delivery.restaurant_service.entity.Restaurant;
import com.Food.Delivery.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<RestaurantResponse> CreateRestuarant(@Valid @RequestBody RestaurantRequest request) {
        return restaurantService.CreateRestuarant(request);
    }

    @GetMapping
    public ApiResponse<List<Restaurant>> getAllRestaurant() {
        return restaurantService.getAllRestaurant();
    }

    @PutMapping
    public ApiResponse<RestaurantResponse> UpdateRestaurant(@Valid @RequestBody UpdateRestaurantRequest request) {
        return restaurantService.UpdateRestaurant(request);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> DeleteRestaurant(@PathVariable Long id) {
        return restaurantService.DeleteRest(id);
    }



}
