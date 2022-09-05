package com.example.order_service.fallback;

import com.example.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("feign调用product-service异常");
        return null;
    }
}
