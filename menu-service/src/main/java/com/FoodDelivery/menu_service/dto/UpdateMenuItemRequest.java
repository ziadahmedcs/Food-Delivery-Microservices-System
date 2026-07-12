package com.FoodDelivery.menu_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuItemRequest {

    @NotNull(message = "Item Id is required")
    private Long id;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Description must not be empty")
    @Size(min = 5, max = 255)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull(message = "Category Id is required")
    private Long categoryId;

    @NotNull(message = "Available is required")
    private Boolean available;

    private String imageUrl;
}