package com.sk.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentsRequest(
       Integer id,
       BigDecimal amount,
       PaymentMethod paymentMethod,
       Integer orderId,
       String orderReference,
       Customer customer



) {
}
