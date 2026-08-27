package com.E_commerce.product_service.Exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFound(ProductNotFoundException ex){
            ErrorMessage errorMessage=ErrorMessage.builder()
            .code("PRODUCT_NOT_FOUND")
            .message(ex.getMessage())
            .timeStamp(Instant.now())
            .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex){
        ErrorMessage errorMessage=ErrorMessage.builder()
        .code("INTERNAL_SERVER_ERROR")
        .message(ex.getMessage())
        // .message("An unexpected error occurred")
        .timeStamp(Instant.now())
        .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex){
        String message=ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error->
            error.getField() +" : "+ error.getDefaultMessage()
        )
        .findFirst()
        .orElse("Invalid Request");

        ErrorMessage errorMessage=ErrorMessage.builder()
        .code("VALIDATION_ERROR")
        .message(message)
        .timeStamp(Instant.now())
        .build();
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
