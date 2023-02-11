package com.github.productapi.controller;

import com.github.productapi.entity.request.CategoryRequest;
import com.github.productapi.entity.response.CategoryResponse;
import com.github.productapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok().body(categoryService.save(categoryRequest));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(categoryService.findAll(pageable));
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable(name = "categoryId") String categoryId) {
        return ResponseEntity.ok().body(categoryService.findById(categoryId));
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "categoryId") String categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.noContent().build();
    }

}
