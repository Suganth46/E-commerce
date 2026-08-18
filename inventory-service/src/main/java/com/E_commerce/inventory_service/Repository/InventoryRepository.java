package com.E_commerce.inventory_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.E_commerce.inventory_service.Model.Inventory;

import java.util.List;
public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    List<Inventory> findBySkucodeIn(List<String> skucode);
}
