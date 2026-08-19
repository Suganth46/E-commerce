package com.E_commerce.inventory_service;

import com.E_commerce.inventory_service.Model.Inventory;
import com.E_commerce.inventory_service.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//        return args -> {
//            Inventory inventory1=new Inventory();
//            inventory1.setSkucode("laptop");
//            inventory1.setQuantity(100);
//
//            Inventory inventory2=new Inventory();
//            inventory2.setSkucode("Phone");
//            inventory2.setQuantity(10);
//
//            inventoryRepository.save(inventory1);
//            inventoryRepository.save(inventory2);
//        };
//    }
}
