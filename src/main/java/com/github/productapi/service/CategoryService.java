package com.github.productapi.service;

import com.github.productapi.entity.Category;
import com.github.productapi.entity.request.CategoryRequest;
import com.github.productapi.entity.response.CategoryResponse;
import com.github.productapi.repository.CategoryRepository;
import com.github.productapi.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse save(CategoryRequest categoryRequest) {
        Category category = new Category();
        fromRequestToEntity(categoryRequest, category);
        categoryRepository.save(category);
        return new CategoryResponse(category);
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(CategoryResponse::new);
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(String categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Category entityCategory = optionalCategory.orElseThrow(
                () -> new NotFoundException("Category with id " + categoryId + " not found"));
        return new CategoryResponse(entityCategory);
    }

    public void deleteById(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private void fromRequestToEntity(CategoryRequest categoryRequest, Category category) {
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
    }

}
