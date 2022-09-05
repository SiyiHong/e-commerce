package com.example.gateway;

import com.example.gateway.filter.IpKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    //限流
    @Bean(name = "ipKeyResolver")
    public KeyResolver userIpKeyResolver() {
        return new IpKeyResolver();
    }


}
