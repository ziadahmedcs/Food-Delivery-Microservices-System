package com.Food.Delivery.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest
{
    @NotBlank(message = "Identifier is required")
    private  String identifier ;
    @NotBlank(message = "Password is required")
    private  String password ;
}
