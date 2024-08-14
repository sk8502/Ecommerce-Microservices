package com.sk.ecommerce.kafka;

import com.sk.ecommerce.customerclient.CustomerResponse;
import com.sk.ecommerce.order.PaymentMethod;
import com.sk.ecommerce.products.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> proucts

) {
}
