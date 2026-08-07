package com.E_commerce.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E_commerce.order_service.Model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
