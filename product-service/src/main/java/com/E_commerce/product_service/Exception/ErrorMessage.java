package com.E_commerce.product_service.Exception;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
    private Instant timeStamp;
}
