package com.E_commerce.product_service.Controller;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request){
        productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllProduct(){
        return productService.findAllProduct();
    }
}
