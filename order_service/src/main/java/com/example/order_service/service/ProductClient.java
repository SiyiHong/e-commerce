package com.example.order_service.service;

import com.example.order_service.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service",fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("product/find")
    String findById(@RequestParam(value = "id")int id);
}
