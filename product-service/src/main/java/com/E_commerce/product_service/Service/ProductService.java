package com.E_commerce.product_service.Service;

import com.E_commerce.product_service.DTO.CreateRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Model.Product;
import com.E_commerce.product_service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(CreateRequest request){
        Product product= Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        productRepository.save(product);
    }

    public List<ProductResponse> findAllProduct() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(p-> ProductResponse.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build()).toList();
    }
}
