package com.sk.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurcheseResponse(

Integer productId,
String name,
String description,
BigDecimal price,
double quantity



) {
}
