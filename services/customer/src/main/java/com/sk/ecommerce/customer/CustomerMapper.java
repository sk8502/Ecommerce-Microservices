package com.sk.ecommerce.customer;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request ==null) {
        return null;
        }

            return Customer.builder()
                    .id(request.id())
                    .fisrtname(request.fisrtname())
                    .lastname(request.lastname())
                    .email(request.email())
                    .address(request.address())
                    .build();




    }

    public CustomerResponse fromCustomer(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFisrtname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()

        );
    }
}
