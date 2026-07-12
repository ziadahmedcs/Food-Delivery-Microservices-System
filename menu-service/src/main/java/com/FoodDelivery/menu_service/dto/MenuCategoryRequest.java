package com.FoodDelivery.menu_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCategoryRequest {
    @NotBlank(message = "name must not be empty")
    @Size(min = 3, max = 15, message = "name must be between 3 and 15 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must contain letters only")
    private  String name ;
   @NotNull(message = "restaurantId mustn't be empty")
    private Long restaurantId ;

}
