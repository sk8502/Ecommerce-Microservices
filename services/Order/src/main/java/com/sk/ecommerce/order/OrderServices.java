package com.sk.ecommerce.order;

import com.sk.ecommerce.customerclient.CustomerClient;
import com.sk.ecommerce.exception.BusinessException;
import com.sk.ecommerce.kafka.OrderConfirmation;
import com.sk.ecommerce.kafka.OrderProducer;
import com.sk.ecommerce.orderLine.OrderLineRequest;
import com.sk.ecommerce.orderLine.OrderlineService;
import com.sk.ecommerce.payment.PaymentRequest;
import com.sk.ecommerce.payment.PaymentsClient;
import com.sk.ecommerce.products.ProductsClient;
import com.sk.ecommerce.products.PurcheseRequest;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServices {

private final CustomerClient customerClient;
private final ProductsClient productClient;
private final OrderRepository repository;
private final OrderMapper mapper;
private final OrderlineService orderlineService;
private final OrderProducer orderProducer;
private final PaymentsClient paymentsClient;

@Transactional
    public Integer createOrder(OrderRequest request) {
        //check the customer --openfign

         var customer =this.customerClient.findCustomerById(request.customerId())
                 .orElseThrow(()-> new BusinessException("Canot create order ::: No customer exist for thst provices id ::-- "+request.customerId()));

        //purchase the products  ---> products microservcies

      var purchasedProduct=  this.productClient.purcheseProduct(request.products());

        //persist order
        var order=this.repository.save(this.mapper.toOrder(request));

        //persist order lines

        for (PurcheseRequest purcheseRequest: request.products()){
               orderlineService.saveOrderLine(new OrderLineRequest(
                       null,order.getId(),
                       purcheseRequest.productId(),
                       purcheseRequest.quantity()
               )
               );
        }

        //start payment process
         var paymentRequest=new PaymentRequest(
                 request.amount(),
                 request.paymentMethod(),
                 order.getId(),
                 order.getReference(),
                 customer
         );
        paymentsClient.requestOrderPayment(paymentRequest);


        //send thye order confimertion ---> notificatio-ms

      orderProducer.sendOrderConfirmation(new OrderConfirmation(
              request.reference(),
              request.amount(),
              request.paymentMethod(),
              customer,
              purchasedProduct
      ));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
     return    repository.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
      return  repository.findById(orderId)
              .map(mapper:: fromOrder)
              .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with Given Id  :  %d",orderId)));

    }
}
