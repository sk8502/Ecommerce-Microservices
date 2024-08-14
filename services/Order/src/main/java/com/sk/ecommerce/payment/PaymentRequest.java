package com.sk.ecommerce.payment;

import com.sk.ecommerce.customerclient.CustomerResponse;
import com.sk.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
