package com.E_commerce.product_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(value = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String skuCode;
    private String description;
    private BigDecimal price;
    private String category;
    private String brand;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
