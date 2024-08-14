package com.sk.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record CustomerRequest(

        String id,
        @NotNull(message = "customer first name is required")
        String fisrtname,
        @NotNull(message = "customer last name is required")
        String lastname,
        @Email(message = "Customer email is not a valid email adress")
        @NotNull(message = "customer Email is required")
        String email,

        Address address) {




}
