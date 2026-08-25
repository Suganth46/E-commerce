package com.E_commerce.product_service.Service;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.DTO.ProductResponse;
import com.E_commerce.product_service.Model.Product;
import com.E_commerce.product_service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest request){
        Product product= Product.builder()
                .name(request.getName())
                .skuCode(request.getSkuCode())
                .price(request.getPrice())
                .description(request.getDescription())
                .brand(request.getBrand())
                .category(request.getCategory())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .active(true)
                .build();
        productRepository.save(product);
        log.info("Product {} saved",product.getId());
        return mapToProductResponse(product);
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
                .createdAt(product.getCreatedAt())
                .category(product.getCategory())
                .brand(product.getBrand())
                .build();
    }
}
