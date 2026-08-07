package com.E_commerce.order_service.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

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

    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemsDto()
        .stream()
        .map(this::mapToDto)
        .toList();

        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
        log.info("Order {} saved",order.getOrderNumber());
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
