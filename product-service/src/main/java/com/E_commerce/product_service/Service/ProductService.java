package com.E_commerce.product_service.Service;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Model.Product;
import com.E_commerce.product_service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest request){
        Product product= Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        productRepository.save(product);
        log.info("Product {} saved",product.getId());
    }

    public List<ProductResponse> findAllProduct() {
        List<Product> products=productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
