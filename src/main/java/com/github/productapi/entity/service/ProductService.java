package com.github.productapi.entity.service;

import com.github.productapi.entity.Product;
import com.github.productapi.entity.request.ProductRequest;
import com.github.productapi.entity.response.ProductResponse;
import com.github.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse save(ProductRequest productRequest) {
        Product product = new Product();
        fromRequestToEntity(productRequest, product);
        productRepository.save(product);
        return new ProductResponse(product);
    }

    private void fromRequestToEntity(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
    }

}
