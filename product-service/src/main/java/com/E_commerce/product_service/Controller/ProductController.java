package com.E_commerce.product_service.Controller;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Validated @RequestBody ProductRequest request){
       ProductResponse productResponse=productService.createProduct(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAllProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<ProductResponse>  products=productService.findAllProduct(page,size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<ProductResponse> findProductById(@PathVariable String id){
        ProductResponse productResponse=productService.findProductById(id);
        return ResponseEntity.ok(productResponse);
    } 
}
