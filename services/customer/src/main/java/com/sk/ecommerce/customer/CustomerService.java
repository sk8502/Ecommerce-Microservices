package com.sk.ecommerce.customer;

import com.sk.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {



   private  final CustomerRepository repository;

    private final CustomerMapper mapper;

   public String createCustomer (CustomerRequest request){
       var customer=repository.save(mapper.toCustomer(request));
       return customer.getId();
   }

    public void updateCustomer(CustomerRequest request) {

       var customer=repository.findById(request.id()).orElseThrow(()-> new CustomerNotFoundException(
               String.format("Can not update Customer :: no customer found with the provide ID:: %s")
       ));
       mergerCustomer(customer,request);
       repository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {

       if(StringUtils.isNotBlank(request.fisrtname())){
           customer.setFisrtname(request.fisrtname());
       }
        if(StringUtils.isNotBlank(request.lastname())){
        customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null ){
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> findAllCustomer() {

     return   repository.findAll().stream().map(mapper::fromCustomer)
               .collect(Collectors.toList());
    };

    public Boolean existisById(String customerId) {
        return repository.findById(customerId).isPresent();
    };

    public CustomerResponse findById(String customerId) {
      return   repository.findById(customerId).map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException("No customer found with the ID  "+customerId));

    }

    public void deleteCustomer(String customerId) {
         repository.deleteById(customerId);
    }
}
