package com.E_commerce.product_service.Service;

import com.E_commerce.product_service.DTO.CreateRequest;
import com.E_commerce.product_service.Model.Product;
import com.E_commerce.product_service.Repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public void createProduct(CreateRequest request){
        Product product= Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        productRepository.save(product);
    }
}
