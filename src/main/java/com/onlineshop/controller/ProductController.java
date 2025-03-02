package com.onlineshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.*;
import com.onlineshop.service.ProductRedisService;
import com.onlineshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRedisService productRedisService;

    @NonFinal
    private final String keyword = "Products";

    @GetMapping
    public ApiResponse<List<ProductResponse>> getProducts() throws JsonProcessingException {
        List<ProductResponse> productResponses = productRedisService.getAllProducts(keyword);
        System.out.println("productResponses: " + productResponses);

        if (productResponses == null) {
            productResponses = productService.getProducts();
            productRedisService.saveAllProducts(productResponses, keyword);
            System.out.println("Here's the products list");
        }

        return ApiResponse.<List<ProductResponse>>builder()
                .result(productResponses)
                .build();
    }

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(productId))
                .build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody @Valid ProductUpdateRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productId, request))
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
