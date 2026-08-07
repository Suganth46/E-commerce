package com.E_commerce.inventory_service.Service;
import com.E_commerce.inventory_service.Repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skucode){
        return inventoryRepository.findBySkucode(skucode).isPresent();
    }
}
