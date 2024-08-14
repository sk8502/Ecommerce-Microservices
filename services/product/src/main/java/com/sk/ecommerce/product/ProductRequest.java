package com.sk.ecommerce.product;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (



                 Integer id,
                 @NotNull(message="Produsct name is required")
                 String name,
                 @NotNull(message="Produsct  description  is required")
                 String description,
                 @Positive(message="Available Produsct quantity   is positive")
                 double availableQuantity,
                 @Positive(message="price is +")
                 BigDecimal price,
                 @NotNull(message="categoryId  is required")
                 Integer categoryId


){

}
