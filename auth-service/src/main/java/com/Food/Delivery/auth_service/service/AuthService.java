package com.Food.Delivery.auth_service.service;

import com.Food.Delivery.auth_service.dto.*;
import com.Food.Delivery.auth_service.entity.Users;
import com.Food.Delivery.auth_service.enums.UserRole;
import com.Food.Delivery.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private  JwtService jwtService ;
    public ApiResponse<Users> RegisterCustomer(RegisterRequest request)
    {
        if (userRepository.existsByEmail(request.getEmail())) {
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Email already exists"
        );
    }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Phone already exists"
            );
        }
        Users user = Users.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .userRole(UserRole.CUSTOMER)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())).build() ;
       Users usersaved =  userRepository.save(user) ;
        Map<String , Object> response = new HashMap<>() ;
        return ApiResponse.<Users>builder().data(usersaved).message("Customer registered successfully").success(true).build();
    }

    public ApiResponse<Map<String ,Object>> Login (LoginRequest loginRequest)
    {
        if (userRepository.findByEmailOrPhone(loginRequest.getIdentifier(), loginRequest.getIdentifier()).isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Identifier Not Exists") ;
        }
        Users users = userRepository.findByEmailOrPhone(loginRequest.getIdentifier(),loginRequest.getIdentifier()) .get() ;
        if (!passwordEncoder.matches(loginRequest.getPassword() ,users.getPassword()))
        {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email/phone or password"
            );        }
        String token = jwtService.generateToken(users);
        System.out.println(token);
        Map <String , Object> response = new HashMap<>() ;
        response.put("data" , users) ;
        response.put("token" ,token) ;
        return ApiResponse.<Map<String ,Object>>builder()
                .data(response)
                .message("Customer registered successfully")
                .success(true)
                .build();
    }

    public  ApiResponse<UserResponse> me ()
    {
        Users user =(Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .role(user.getUserRole())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
        return ApiResponse.<UserResponse>builder().success(true)
                .data(userResponse)
                .build();
    }
    public ApiResponse<String> ChangePassword (ChangePasswordRequest request)
    {
        Users user = (Users) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Old password is incorrect"
            );
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "New password must be different from the old password"
            );
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "New password and confirm password do not match"
            );
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Password changed successfully")
                .build();
    }

    public  ApiResponse<UserResponse> CreateOwner (CreateOwnerRequest request)
    {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already exists"
            );
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Phone already exists"
            );
        }
        Users users = Users.builder().name(request.getName())
                .email(request.getEmail())
                .userRole(UserRole.RESTAURANT_OWNER)
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .build() ;
        Users usersaved =  userRepository.save(users) ;
        UserResponse userResponse = UserResponse.builder()
                .phone(usersaved.getPhone())
                .role(UserRole.RESTAURANT_OWNER)
                .name(usersaved.getName())
                .email(usersaved.getEmail())
                .id(usersaved.getId())
                .build();
        return ApiResponse.<UserResponse>builder().data(userResponse).message("Owner created successfully").success(true).build();

    }
    public  ApiResponse<UserResponse>getUserById (Long id )
    {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));
        UserResponse userResponse = UserResponse.builder()
                .id(users.getId())
                .email(users.getEmail())
                .name(users.getName())
                .role(users.getUserRole())
                .phone(users.getPhone())
                .build();
        return  ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .success(true)
                .build();
    }
}
