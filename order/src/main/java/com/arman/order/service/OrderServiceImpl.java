package com.arman.order.service;

import com.arman.order.dto.OrderLineItemsDto;
import com.arman.order.dto.OrderRequest;
import com.arman.order.model.Order;
import com.arman.order.model.OrderLineItems;
import com.arman.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::getOrderLineItemFromOrderRequest).toList();
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
        log.info("Order has been placed successfully");
    }

    private OrderLineItems getOrderLineItemFromOrderRequest(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
