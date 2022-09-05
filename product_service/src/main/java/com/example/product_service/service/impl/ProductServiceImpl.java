package com.example.product_service.service.impl;

import com.example.product_service.domain.Product;
import com.example.product_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    //此处模拟数据库dao
    private static final Map<Integer,Product> daoMap = new HashMap<>();
    static {
        Product p1 = new Product(1,"qw",123,10);
        Product p2 = new Product(2,"er",124,11);
        Product p3 = new Product(3,"ty",127,10);
        daoMap.put(p1.getId(),p1);
        daoMap.put(p2.getId(),p2);
        daoMap.put(p3.getId(),p3);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection= daoMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        logger.info("service findById");
        return daoMap.get(id);
    }
}
