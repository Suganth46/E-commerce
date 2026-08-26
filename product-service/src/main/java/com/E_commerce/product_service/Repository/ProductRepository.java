package com.E_commerce.product_service.Repository;

import com.E_commerce.product_service.Model.Product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
