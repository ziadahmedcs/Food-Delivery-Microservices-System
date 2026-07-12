package com.Food.Delivery.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    @NotBlank(message = "Old Password must not be empty")
    @Size(min = 6, max = 15, message = "Old Password must be between 6 and 15 characters")
    private String oldPassword ;
    @NotBlank(message = "New Password must not be empty")
    @Size(min = 6, max = 15, message = "New Password must be between 6 and 15 characters")
    private String newPassword ;
    @NotBlank(message = "Confirm Password must not be empty")
    @Size(min = 6, max = 15, message = "Confirm Password must be between 6 and 15 characters")
    private  String confirmPassword ;
}
