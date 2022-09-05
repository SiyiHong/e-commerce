package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class IpKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String hostName = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        System.out.println("hostName:"+hostName);
        return Mono.just(hostName);
    }
}

