package com.onlineshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.onlineshop.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRedisService {
    private final RedisTemplate<String, Object> redistemplate;
    private final ObjectMapper redisObjectMapper;

    private String getKeyFrom(String keyword) {
        return String.format("all_products:%s", keyword);
    }

    public List<ProductResponse> getAllProducts(String keyword) throws JsonProcessingException {
        String key = this.getKeyFrom(keyword);
        String json = (String) redistemplate.opsForValue().get(key);
        List<ProductResponse> productResponses =
                json != null ?
                redisObjectMapper.readValue(json, new TypeReference<List<ProductResponse>>() {}) : null;
        return productResponses;
    }

    public void clearRedisCache() {
        redistemplate.getConnectionFactory().getConnection().flushAll();
    }

    public void saveAllProducts(List<ProductResponse> productResponses, String keyword)
            throws JsonProcessingException
    {
        String key = this.getKeyFrom(keyword);
        String json = redisObjectMapper.writeValueAsString(productResponses);
        redistemplate.opsForValue().set(key, json);
    }
}
