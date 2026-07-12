package com.FoodDelivery.menu_service.controller;

import com.FoodDelivery.menu_service.dto.*;
import com.FoodDelivery.menu_service.entity.MenuCategory;
import com.FoodDelivery.menu_service.entity.MenuItem;
import com.FoodDelivery.menu_service.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal")
public class MenuInternalController {

    @Autowired
    private MenuService menuService ;


    @GetMapping("/item/{id}")
    public ApiResponse<MenuItemResponse> GetITEM (@PathVariable Long id)
    {
        return menuService.GetItemById(id);
    }

}
