package com.example.product_service.controller;

import com.example.product_service.service.ProductService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("product")
@RefreshScope
public class ProductController {

    @Value("${server.port}")
    private String port;
    @Value("${env}")
    private String env;
    @Autowired
    private ProductService productService;

    @RequestMapping("list")
    public Object list(){
        return productService.listProduct();
    }

    @RequestMapping("find")
    public Object findById(@RequestParam("id") int id){
        System.out.println("from port:"+port);
        System.out.println("from env"+env);
        return productService.findById(id);
    }
}
