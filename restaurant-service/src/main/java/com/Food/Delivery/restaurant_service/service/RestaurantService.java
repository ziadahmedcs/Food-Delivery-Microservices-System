package com.Food.Delivery.restaurant_service.service;

import com.Food.Delivery.restaurant_service.client.AuthClient;
import com.Food.Delivery.restaurant_service.dto.*;
import com.Food.Delivery.restaurant_service.entity.Restaurant;
import com.Food.Delivery.restaurant_service.enums.RestaurantStatus;
import com.Food.Delivery.restaurant_service.enums.UserRole;
import com.Food.Delivery.restaurant_service.repository.RestuarantRepository;
import com.Food.Delivery.restaurant_service.security.AuthenticatedUser;
import com.Food.Delivery.restaurant_service.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class RestaurantService {
    @Autowired
    private RestuarantRepository restuarantRepository ;
    @Autowired
    private  AuthClient authClient ;
    public ApiResponse<RestaurantResponse> CreateRestuarant (RestaurantRequest request)
    {
        if (restuarantRepository.existsByName(request.getName()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"Restaurant name already exists") ;
        }
        if (restuarantRepository.existsByPhone(request.getPhone()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"Restaurant phone already exists") ;
        }
        if (restuarantRepository.existsByOwnerId(request.getOwnerId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This owner already has a restaurant"
            );
        }
       UserResponse user = authClient.getUserById(request.getOwnerId()).getData();
        if (!user.getRole().equals(UserRole.RESTAURANT_OWNER))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"User is not a restaurant owner") ;

        }
        Restaurant restaurant = Restaurant.builder()
                .phone(request.getPhone())
                .name(request.getName())
                .ownerId(user.getId())
                .description(request.getDescription())
                .address(request.getAddress())
                .restaurantStatus(RestaurantStatus.ACTIVE)
                .build();
      Restaurant saved = restuarantRepository.save(restaurant) ;
      RestaurantResponse response = RestaurantResponse.builder()
              .id(saved.getId())
              .address(saved.getAddress())
              .restaurantStatus(RestaurantStatus.ACTIVE)
              .phone(saved.getPhone())
              .ownerId(saved.getOwnerId())
              .name(saved.getName())
              .description(saved.getDescription())
              .build();
      return ApiResponse.<RestaurantResponse>builder()
              .data(response)
              .success(true)
              .message("Restaurant created successfully")
              .build();
    }

    public ApiResponse<List<Restaurant>> getAllRestaurant ()
    {
        List<Restaurant> restaurantList = restuarantRepository.findAll() ;
        return  ApiResponse.<List<Restaurant>>builder()
                .message("Get All Restaurant")
                .data(restaurantList)
                .success(true)
                .build();
    }
    public ApiResponse<RestaurantResponse> UpdateRestaurant (UpdateRestaurantRequest request)
    {
        if (restuarantRepository.findById(request.getRestaurantId()).isEmpty())
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , "The Restaurant not found") ;
        }
        if (restuarantRepository.existsByName(request.getName()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"Restaurant name already exists") ;
        }
        if (restuarantRepository.existsByPhone(request.getPhone()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"Restaurant phone already exists") ;
        }

       Restaurant restaurant = restuarantRepository.getById(request.getRestaurantId());
        restaurant.setAddress(restaurant.getAddress());
        restaurant.setName(request.getName());
        restaurant.setPhone(request.getPhone());
        restaurant.setRestaurantStatus( RestaurantStatus.ACTIVE);
        restaurant.setDescription(request.getDescription());
        Restaurant saved = restuarantRepository.save(restaurant) ;
        RestaurantResponse response = RestaurantResponse.builder()
                .id(saved.getId())
                .address(saved.getAddress())
                .restaurantStatus(saved.getRestaurantStatus())
                .phone(saved.getPhone())
                .ownerId(saved.getOwnerId())
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
        return ApiResponse.<RestaurantResponse>builder()
                .data(response)
                .success(true)
                .message("Restaurant Updated successfully")
                .build();
    }
    public  ApiResponse<String> DeleteRest (Long id )
    {
        if (restuarantRepository.findById(id).isEmpty())
        {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND , "The Restaurant not found") ;
        }
        restuarantRepository.deleteById(id);
        return ApiResponse.<String>builder().
                data("Restaurant Updated successfully")
                .success(true)
                .build();
    }
// -------------------------------------------------The Owner Service-------------------------------------------------------------------------
    public ApiResponse<Restaurant> GetMyRes ()
    {

        AuthenticatedUser authenticatedUser =SecurityUtils.getCurrentUser() ;
        Restaurant restaurant =restuarantRepository.findByOwnerId(authenticatedUser.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND , "The User N't Have Restaurant")) ;
        return ApiResponse.<Restaurant>builder()
                .data(restaurant)
                .success(true)
                .build();

    }

    public ApiResponse<Restaurant> UpdateResOwner (UpdateRestaurantOwnerRequest request)
    {
        AuthenticatedUser authenticatedUser = SecurityUtils.getCurrentUser() ;

        Restaurant restaurant =restuarantRepository.findByOwnerId(authenticatedUser.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND , "The User N't Have Restaurant")) ;
        restaurant.setAddress(request.getAddress());
        restaurant.setRestaurantStatus(request.getRestaurantStatus());
        restaurant.setPhone(request.getPhone());
        restaurant.setDescription(request.getDescription());
        restuarantRepository.save(restaurant) ;
        return ApiResponse.<Restaurant>builder()
                .data(restaurant)
                .success(true)
                .build();

    }

    public  ApiResponse<RestaurantResponse> findResById(Long id)
    {
        if(!restuarantRepository.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "The Restaurant isn't found") ;
        }
        Restaurant restaurant = restuarantRepository.getById(id) ;
        RestaurantResponse response = RestaurantResponse.builder()
                .description(restaurant.getDescription())
                .id(restaurant.getId())
                .name(restaurant.getName())
                .ownerId(restaurant.getOwnerId())
                .phone(restaurant.getPhone())
                .restaurantStatus(restaurant.getRestaurantStatus())
                .address(restaurant.getAddress())
                .build();
        return ApiResponse.<RestaurantResponse>builder()
                .data(response)
                .success(true)
                .build();

    }
}
