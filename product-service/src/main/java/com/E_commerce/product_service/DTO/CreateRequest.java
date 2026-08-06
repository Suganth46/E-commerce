package com.E_commerce.product_service.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class CreateRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
