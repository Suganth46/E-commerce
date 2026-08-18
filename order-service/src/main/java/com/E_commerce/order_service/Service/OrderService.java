package com.E_commerce.order_service.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

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
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDto()
        .stream()
        .map(this::mapToDto)
        .toList();

        List<String> sukCodes=order.getOrderLineItems()
        .stream()
        .map(OrderLineItems::getSkucode)
        .toList();

        order.setOrderLineItems(orderLineItems);
        // Call Inventory Service if the product is availabe in stock
        Boolean result=webClient.get()
        .uri("http://localhost:8082/api/inventory", UriBuilder -> UriBuilder.queryParam("sukCode", sukCodes).build())
        .retrieve()
        .bodyToMono(Boolean.class)
        .block();
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
