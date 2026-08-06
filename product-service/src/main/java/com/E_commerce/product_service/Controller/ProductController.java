package com.E_commerce.product_service.Controller;

import com.E_commerce.product_service.DTO.CreateRequest;
import com.E_commerce.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CreateRequest request){
        productService.createProduct(request);
    }

    @GetMapping

}
