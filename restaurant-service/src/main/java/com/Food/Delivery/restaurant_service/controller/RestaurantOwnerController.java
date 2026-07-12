package com.Food.Delivery.restaurant_service.controller;

import com.Food.Delivery.restaurant_service.dto.*;
import com.Food.Delivery.restaurant_service.entity.Restaurant;
import com.Food.Delivery.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/restaurant")
@RequiredArgsConstructor
public class RestaurantOwnerController {
  private  final RestaurantService restaurantService ;
  @GetMapping
    public  ApiResponse<Restaurant>GetMyRes ()
  {
      return  restaurantService.GetMyRes() ;
  }
  @PutMapping
    public  ApiResponse<Restaurant>UpdateRes (@RequestBody UpdateRestaurantOwnerRequest request)
  {
      return  restaurantService.UpdateResOwner(request) ;
  }
}
