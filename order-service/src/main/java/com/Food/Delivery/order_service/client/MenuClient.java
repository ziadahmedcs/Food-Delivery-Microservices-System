package com.Food.Delivery.order_service.client;

import com.Food.Delivery.order_service.dto.ApiResponse;
import com.Food.Delivery.order_service.dto.MenuItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;


@FeignClient(name = "menu-service")
public interface MenuClient {

        @GetMapping("/internal/item/{id}")
        public ApiResponse<MenuItemResponse> GetITEM (@PathVariable Long id);
    }

