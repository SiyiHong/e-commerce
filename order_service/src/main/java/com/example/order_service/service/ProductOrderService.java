package com.example.order_service.service;

import com.example.order_service.domain.ProductOrder;

public interface ProductOrderService {
    ProductOrder save(int userId, int productId);
}
