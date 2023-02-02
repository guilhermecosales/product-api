package com.github.productapi.controller;

import com.github.productapi.entity.request.ProductRequest;
import com.github.productapi.entity.response.ProductResponse;
import com.github.productapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.save(productRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(productService.findAll(pageable));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable(name = "productId") String productId) {
        return ResponseEntity.ok().body(productService.findById(productId));
    }

}
