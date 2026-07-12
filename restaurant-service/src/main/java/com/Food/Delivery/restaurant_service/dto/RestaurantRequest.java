package com.Food.Delivery.restaurant_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {

    @NotBlank(message = "name must not be empty")
    @Size(min = 3, max = 15, message = "name must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must contain letters only")
    private  String name ;
    @NotBlank(message = "description must not be empty")
    @Size(min = 3, max = 15, message = "description must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "description must contain letters only")
    private  String description ;
    @NotBlank(message = "address must not be empty")
    @Size(min = 3, max = 15, message = "address must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "address must contain letters only")
    private  String address ;
    @Pattern(
            regexp="^01[0125]\\d{8}$"
    )
    private  String phone ;
    private  Long ownerId ;
}
