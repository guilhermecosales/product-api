package com.github.productapi.entity.response;

import com.github.productapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();
    private CategoryResponse category;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.tags.addAll(product.getTags());
        this.category = new CategoryResponse(product.getCategory());
    }

}
