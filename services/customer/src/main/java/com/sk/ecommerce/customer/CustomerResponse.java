package com.sk.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse (
        String id,
        String fisrtname,
        String lastname,
        String email,

        Address address

){

}
