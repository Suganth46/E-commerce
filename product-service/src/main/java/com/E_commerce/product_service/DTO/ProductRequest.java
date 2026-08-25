package com.E_commerce.product_service.DTO;

import lombok.*;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String skuCode;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    private String category;
    private String brand;
}
