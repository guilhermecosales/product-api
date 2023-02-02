package com.github.productapi.service;

import com.github.productapi.entity.Product;
import com.github.productapi.entity.request.ProductRequest;
import com.github.productapi.entity.response.ProductResponse;
import com.github.productapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductResponse::new);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product entityProduct = optionalProduct.orElseThrow(
                () -> new RuntimeException("Product with id " + productId + " not found"));
        return new ProductResponse(entityProduct);
    }

    public void deleteById(String productId) {
        productRepository.deleteById(productId);
    }

    private void fromRequestToEntity(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
    }

}
