package com.sk.ecommerce.orderLine;


import com.sk.ecommerce.order.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
     return OrderLine.builder()
             .id(request.id())
             .quantity(request.quantity())
             .order(
                     OrderEntity.builder()
                             .id(request.orderId())
                             .build()
             )
             .productId(request.productId())
         .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());

    }
}
