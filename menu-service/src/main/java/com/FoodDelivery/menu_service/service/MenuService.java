package com.FoodDelivery.menu_service.service;

import com.FoodDelivery.menu_service.client.RestaurantClient;
import com.FoodDelivery.menu_service.dto.*;
import com.FoodDelivery.menu_service.entity.MenuCategory;
import com.FoodDelivery.menu_service.entity.MenuItem;
import com.FoodDelivery.menu_service.enums.UserRole;
import com.FoodDelivery.menu_service.repository.MenuCategoryRepository;
import com.FoodDelivery.menu_service.repository.MenuItemRepository;
import com.FoodDelivery.menu_service.security.AuthenticatedUser;
import com.FoodDelivery.menu_service.security.SecurityUtils;
import feign.FeignException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private RestaurantClient restaurantClient ;
    @Autowired
    private MenuCategoryRepository menuCategoryRepository ;
    @Autowired
    private MenuItemRepository menuItemRepository ;

    public ApiResponse<String>CreateCategory (MenuCategoryRequest request)
    {
        if(menuCategoryRepository.existsByNameAndRestaurantId(request.getName(), request.getRestaurantId()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT , "The Category Is Exists")  ;
        }
        RestaurantResponse response;

        try {
            response = restaurantClient.findByResId(request.getRestaurantId()).getData();
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "The Restaurant isn't found"
            );
        }
        AuthenticatedUser authenticatedUser=SecurityUtils.getCurrentUser() ;
        if(!response.getOwnerId().equals(authenticatedUser.getId()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT ,"That’s not the restaurant owner")  ;
        }
        MenuCategory menuCategory = MenuCategory.builder()
                .name(request.getName())
                .restaurantId(request.getRestaurantId())
                .build();
        menuCategoryRepository.save(menuCategory) ;
        return ApiResponse.<String>builder()
                .data("The Category Created Successes")
                .success(true)
                .build();
    }

    public List<MenuCategory>GetMYGategory (Long id)
    {

        return  menuCategoryRepository.findByRestaurantId(id);
    }
    public MenuCategory getOneCategory (Long id)
    {
        return  menuCategoryRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND ,"The Category Not Found")) ;
    }
    public ApiResponse<MenuItemResponse> createMenuItem(MenuItemRequest request) {

        // Check Category
        MenuCategory category = menuCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Category not found"
                        )
                );

        // Check Duplicate
        if (menuItemRepository.existsByNameAndCategoryId(
                request.getName(),
                request.getCategoryId())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Menu Item already exists"
            );
        }

        // Check Restaurant
        RestaurantResponse restaurant =
                restaurantClient.findByResId(category.getRestaurantId()).getData();

        // Check Owner
        AuthenticatedUser currentUser = SecurityUtils.getCurrentUser();

        if (!restaurant.getOwnerId().equals(currentUser.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not the restaurant owner"
            );
        }

        // Save
        MenuItem item = MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .available(request.getAvailable())
                .imageUrl(request.getImageUrl())
                .categoryId(category.getId())
                .restaurantId(category.getRestaurantId())
                .build();

        MenuItem saved = menuItemRepository.save(item);

        MenuItemResponse response = MenuItemResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .available(saved.isAvailable())
                .imageUrl(saved.getImageUrl())
                .categoryId(saved.getCategoryId())
                .restaurantId(saved.getRestaurantId())
                .build();

        return ApiResponse.<MenuItemResponse>builder()
                .success(true)
                .message("Menu Item created successfully")
                .data(response)
                .build();
    }
    public MenuItem getItemById (Long id)
    {
        return menuItemRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"The Item Not Found"));
    }
    public ApiResponse<MenuItemResponse>GetItemById (Long id)
    {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"The Item Not Found"));
        MenuItemResponse response =MenuItemResponse.builder()
                .categoryId(menuItem.getCategoryId())
                .price(menuItem.getPrice())
                .name(menuItem.getName())
                .restaurantId(menuItem.getRestaurantId())
                .imageUrl(menuItem.getImageUrl())
                .description(menuItem.getDescription())
                .available(menuItem.isAvailable())
                .id(menuItem.getId())
                .build() ;
        return  ApiResponse.<MenuItemResponse>builder().success(true)
                .data(response)
                .build();
    }
    public List<MenuItem> getItemByCategory ( Long categoryId)
    {
        return  menuItemRepository.findByCategoryId(categoryId) ;
    }
    public List<MenuItem>getItemByRestaurant ( Long restaurantId )
    {
        return menuItemRepository.findByRestaurantId(restaurantId)  ;
    }
    public MenuItem UpdateItem (UpdateMenuItemRequest request)
    {
        MenuItem item = menuItemRepository.findById(request.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"The Item Not Found")) ;
        MenuCategory menuCategory = menuCategoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The Category Not Found")) ;
        RestaurantResponse response =restaurantClient.findByResId(menuCategory.getRestaurantId()).getData();
         AuthenticatedUser user=SecurityUtils.getCurrentUser() ;
         if (!user.getId().equals(response.getOwnerId()))
         {
             throw new ResponseStatusException(HttpStatus.FORBIDDEN,"That’s not the restaurant owner") ;
         }
         item.setName(request.getName());
         item.setAvailable(request.getAvailable());
         item.setPrice(request.getPrice());
         item.setDescription(request.getDescription());
         item.setCategoryId(request.getCategoryId());
         item.setRestaurantId(menuCategory.getRestaurantId());
         item.setImageUrl(request.getImageUrl());
         return menuItemRepository.save(item) ;
    }
}
