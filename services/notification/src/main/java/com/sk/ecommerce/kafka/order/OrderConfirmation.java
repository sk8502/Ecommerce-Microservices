package com.sk.ecommerce.kafka.order;

import com.sk.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

       String orderReference,
        BigDecimal totalAmount,
        PaymentMethod payamentMethod,
       Customer customer,
       List<Product> products

) {
}
