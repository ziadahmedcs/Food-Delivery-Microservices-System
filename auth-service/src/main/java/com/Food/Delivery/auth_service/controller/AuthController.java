package com.Food.Delivery.auth_service.controller;

import com.Food.Delivery.auth_service.dto.*;
import com.Food.Delivery.auth_service.entity.Users;
import com.Food.Delivery.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register-customer")
    public ApiResponse<Users> register(@Valid @RequestBody RegisterRequest request) {
        return authService.RegisterCustomer(request);
    }
    @PostMapping("/login")
    public ApiResponse<Map<String,Object>> Login (@Valid @RequestBody LoginRequest request) {
        return authService.Login(request);
    }
    @GetMapping("/me")
    public ApiResponse<UserResponse> me () {
        return authService.me();
    }
    @PostMapping("/change-password")
    public ApiResponse<String>ChanegePassword (@Valid @RequestBody ChangePasswordRequest request)
    {
        return  authService.ChangePassword(request) ;
    }
    @PostMapping("/admin/create-owner")
    public  ApiResponse<UserResponse> CreateOwner (@RequestBody @Valid CreateOwnerRequest request)
    {
        return  authService.CreateOwner(request) ;
    }
    @GetMapping("/users/{id}")
    public  ApiResponse<UserResponse> getUserById (@PathVariable Long id )
    {
        return  authService.getUserById(id) ;
    }
}
