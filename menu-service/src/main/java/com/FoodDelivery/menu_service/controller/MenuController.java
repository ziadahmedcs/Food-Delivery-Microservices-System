package com.FoodDelivery.menu_service.controller;

import com.FoodDelivery.menu_service.dto.*;
import com.FoodDelivery.menu_service.entity.MenuCategory;
import com.FoodDelivery.menu_service.entity.MenuItem;
import com.FoodDelivery.menu_service.security.SecurityUtils;
import com.FoodDelivery.menu_service.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService ;
    @PostMapping
    public ApiResponse<String>CreateCategory (@RequestBody @Valid MenuCategoryRequest request)
    {
         return menuService.CreateCategory(request);
    }

    @GetMapping("/{id}")
    public List<MenuCategory> getMyCategory(@PathVariable Long id)
    {
        return menuService.GetMYGategory(id) ;
    }
    @PostMapping("/item")
    public ApiResponse<MenuItemResponse>CreateItemCategory (@RequestBody @Valid MenuItemRequest request)
    {
        return menuService.createMenuItem(request);
    }

    @GetMapping("/item/{id}")
    public MenuItem GetITEM (@PathVariable Long id)
    {
        return menuService.getItemById(id);
    }
    @GetMapping("/categories/{categoryId}/items")
    public List<MenuItem> getItemByCategory (@PathVariable Long categoryId)
    {
        return  menuService.getItemByCategory(categoryId) ;
    }
    @GetMapping("/restaurants/{restaurantId}/items")
    public List<MenuItem>getItemByRestaurant (@PathVariable Long restaurantId )
    {
        return  menuService.getItemByRestaurant(restaurantId) ;
    }
    @PutMapping("/item")
    public MenuItem UpdateItem (@RequestBody @Valid UpdateMenuItemRequest request)
    {
        return  menuService.UpdateItem(request) ;
    }
}
