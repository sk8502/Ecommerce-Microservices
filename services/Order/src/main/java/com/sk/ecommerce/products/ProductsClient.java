package com.sk.ecommerce.products;

import com.sk.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductsClient {

    @Value("${application.config.product-url}")
    private String productsUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purcheseProduct(List<PurcheseRequest> requestBody){

        HttpHeaders headers=new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurcheseRequest>> requestEntity=new HttpEntity<>(requestBody,headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType=
                new ParameterizedTypeReference<>(){};
        ResponseEntity<List<PurchaseResponse>> responseEntity=restTemplate.exchange(productsUrl+"/purchase", POST,requestEntity,responseType);

        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("An error occure while processing the product purchese ===  "+responseEntity.getStatusCode());

        }
        return  responseEntity.getBody();



    }
}
