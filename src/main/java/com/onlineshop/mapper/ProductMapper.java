package com.onlineshop.mapper;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.ProductResponse;
import com.onlineshop.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct (ProductCreationRequest request);

    ProductResponse toProductResponse (Product product);

    void updateProduct (@MappingTarget Product product, ProductUpdateRequest request);
}
