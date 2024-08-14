package com.sk.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request){

   return ResponseEntity.ok(productService.createProduct(request));

    }


    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurcheseResponse>> purcheseProduct(@RequestBody @Valid  List<ProductPurcheseRequest> requests){

     return    ResponseEntity.ok(productService.purcheseProduct(requests));
    }

    @GetMapping("/productId")
    public ResponseEntity<ProductResponse> findByid(@PathVariable Integer productId){
        return ResponseEntity.ok(productService.findById(productId));

    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }


}
