package com.E_commerce.inventory_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.E_commerce.inventory_service.Model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    Optional<Inventory> findBySkucode(String skucode);
}
