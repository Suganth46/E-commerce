package com.E_commerce.product_service.Exception;

public class DuplicateSkuException extends RuntimeException {
    public DuplicateSkuException(String message){
        super(message);
    }
}
