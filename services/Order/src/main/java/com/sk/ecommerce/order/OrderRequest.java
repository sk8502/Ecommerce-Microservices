package com.sk.ecommerce.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sk.ecommerce.products.PurcheseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
@JsonInclude(Include.NON_EMPTY)
public record OrderRequest(Integer id,
                           String reference,
                           @Positive(message = "order amount should be positive")
                           BigDecimal amount,
                           @NotNull(message = "Payment method should not mbe null")
                           PaymentMethod paymentMethod,
                           @NotNull(message = "Customer should be present")
                           @NotEmpty(message = "Customer should be present")
                           @NotBlank(message = "Customer should be present")
                           String customerId,
                           @NotEmpty(message = "You should atleast purchese 1 products")
                           List<PurcheseRequest> products
                           ) {
}
