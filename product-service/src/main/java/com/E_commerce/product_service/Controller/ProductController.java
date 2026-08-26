package com.E_commerce.product_service.Controller;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@Validated @RequestBody ProductRequest request){
        return productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> findAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return productService.findAllProduct(page,size);
    }
}
