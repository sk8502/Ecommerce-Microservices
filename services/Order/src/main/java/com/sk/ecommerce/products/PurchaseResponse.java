package com.sk.ecommerce.products;

import java.math.BigDecimal;

public record PurchaseResponse(


        Integer productId,
        String name,
        String descriptions,
        BigDecimal price,
        double quantity


) {
}
