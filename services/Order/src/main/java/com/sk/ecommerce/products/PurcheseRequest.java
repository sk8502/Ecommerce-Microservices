package com.sk.ecommerce.products;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurcheseRequest(

        @NotNull(message = "products is manditory")
        Integer productId,
        @Positive
        double quantity


) {
}
