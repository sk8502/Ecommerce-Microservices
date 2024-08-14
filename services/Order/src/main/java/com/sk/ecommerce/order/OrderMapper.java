package com.sk.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderEntity toOrder(OrderRequest request) {
    	 if (request == null) {
    	      return null;
    	    }
        return OrderEntity.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .amount(request.amount())
                .paymentsMethod(request.paymentMethod())
        .build();
    }

    public OrderResponse fromOrder(OrderEntity orderEntity) {
       return new OrderResponse(
               orderEntity.getId(),
               orderEntity.getReference(),
               orderEntity.getAmount(),
               orderEntity.getPaymentsMethod(),
               orderEntity.getCustomerId()
       );

    }
}
