package com.sk.ecommerce.product;

import com.sk.ecommerce.exception.ProductPurcheseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final  ProductRepository productRepository;
   private  final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {

        var product=mapper.toProduct(request);

        return  productRepository.save(product).getId();
    }

    @Transactional(rollbackFor = ProductPurcheseException.class)
    public List<ProductPurcheseResponse> purcheseProduct(List<ProductPurcheseRequest> requests) {


        var productId=requests.stream().map(ProductPurcheseRequest::productId).toList();
        var storedProduct=productRepository.findAllByIdInOrderById(productId);
        if(productId.size() != storedProduct.size()){
            throw new ProductPurcheseException("One or more product didn't exist");
        }
        var storedRequest=requests.stream()
                .sorted(Comparator.comparing(ProductPurcheseRequest::productId))
                .toList();
        var purchesdProduct=new ArrayList<ProductPurcheseResponse>();
        for (int i=0;i<storedProduct.size();i++){
            var product=storedProduct.get(i);
            var productRequest=storedRequest.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurcheseException("Insufficent Stock quantity for product with that id===  :  "+ productRequest.productId());
            }
            var newAvailablequantity=product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailablequantity);
            productRepository.save(product);
            purchesdProduct.add(mapper.toProductPurcheseResponse(product,productRequest.quantity()));
        }
    return purchesdProduct;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("produdct Not found with this ID  -- "+productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
