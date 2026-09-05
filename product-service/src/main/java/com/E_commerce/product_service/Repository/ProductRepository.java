package com.E_commerce.product_service.Repository;

import com.E_commerce.product_service.Model.Product;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

    Page<Product> findByActiveTrue(Pageable pageable);

    boolean existsBySkuCode(String skuCode);

    boolean existsBySkuCodeAndIdNot(String skuCode, String id);
}
