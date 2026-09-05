package com.E_commerce.product_service.Exception;

public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(String message){
        super(message);
    }
}
