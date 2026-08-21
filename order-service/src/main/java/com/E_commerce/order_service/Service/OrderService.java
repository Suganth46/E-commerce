package com.E_commerce.order_service.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.E_commerce.order_service.DTO.InventoryResponse;
import com.E_commerce.order_service.DTO.OrderLineItemsDto;
import com.E_commerce.order_service.DTO.OrderRequest;
import com.E_commerce.order_service.Model.Order;
import com.E_commerce.order_service.Model.OrderLineItems;
import com.E_commerce.order_service.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDto()
        .stream()
        .map(this::mapToDto)
        .toList();

        order.setOrderLineItems(orderLineItems);
        List<String> skuCodes=order.getOrderLineItems()
        .stream()
        .map(OrderLineItems::getSkucode)
        .toList();

        // Call Inventory Service if the product is availabe in stock
       InventoryResponse[] inventoryResponsesArray=webClientBuilder.build().get()
        .uri("http://inventory-service/api/inventory", UriBuilder -> UriBuilder.queryParam("skuCode", skuCodes).build())
        .retrieve()
        .bodyToMono(InventoryResponse[].class)
        .block();
        Boolean result=orderLineItems.stream().allMatch(
            o-> Arrays.stream(inventoryResponsesArray)
                .anyMatch(inventory->
                   inventory.getSukCode().equals(o.getSkucode()) 
                   && inventory.getQuantity()>=o.getQuantity()
                )
        );
        if (result) {
            orderRepository.save(order);
            log.info("Order {} saved",order.getOrderNumber());
        }
        else{
            throw new IllegalArgumentException("The Product is Not in Stock, Please Try Again Later");
        }
    }
    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        return OrderLineItems.builder()
        .id(orderLineItemsDto.getId())
        .price(orderLineItemsDto.getPrice())
        .skucode(orderLineItemsDto.getSkucode())
        .quantity(orderLineItemsDto.getQuantity())
        .build();
    }
}
