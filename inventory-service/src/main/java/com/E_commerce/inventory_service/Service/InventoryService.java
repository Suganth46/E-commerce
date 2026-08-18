package com.E_commerce.inventory_service.Service;
import com.E_commerce.inventory_service.DTO.InventoryResponse;
import com.E_commerce.inventory_service.Repository.InventoryRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skucode){
        return inventoryRepository.findBySkucodeIn(skucode).stream()
        .map(inventory->
            InventoryResponse.builder()
            .sukCode(inventory.getSkucode())
            .isInStock(inventory.getQuantity()>0)
            .build()
        ).toList();
    }
}
