package com.example.order_service.service.impl;

import com.example.order_service.domain.ProductOrder;
import com.example.order_service.service.ProductClient;
import com.example.order_service.service.ProductOrderService;
import com.example.order_service.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public ProductOrder save(int userId, int productId) {
        //获取商品详情, loadbalancer方法
//        Map<String,Object> map = restTemplate.getForObject("http://product-service/product/find?id="+productId,Map.class);
//        System.out.println(map);
        //获取商品详情，feign方法
        logger.info("service save");
        String response = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
