package com.Food.Delivery.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "name must not be empty")
    @Size(min = 3, max = 15, message = "First name must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must contain letters only")
    private String name;


    @Email(message = "Must enter a valid email")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
    private String password;

    @NotBlank(message = "Phone must not be empty")
    @Pattern(regexp = "^01[0125]\\d{8}$", message = "Invalid Egyptian phone number format")
    private String phone;
}