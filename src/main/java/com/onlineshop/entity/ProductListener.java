package com.onlineshop.entity;

import com.onlineshop.service.ProductRedisService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductListener {

    private final ProductRedisService productRedisService;

    @PostPersist
    public void postPersist(Product product) {
        log.info("Product created: {}", product.getId());
        productRedisService.clearRedisCache();
    }

    @PreUpdate
    public void preUpdate(Product product) {
        log.info("Product pre updated: {}", product.getId());
    }

    @PostUpdate
    public void postUpdate(Product product) {
        log.info("Product updated: {}", product.getId());
        productRedisService.clearRedisCache();
    }

    @PostRemove
    public void preRemove(Product product) {
        log.info("Product deleted: {}", product.getId());
        productRedisService.clearRedisCache();
    }
}
