package com.sk.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurcheseRequest(


        @NotNull(message = "Product is mendatory")
        Integer productId,
        @NotNull(message = "Quantity is mendatory")
        double quantity



) {
}
