package edu.scnu.train12306.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author long
 * @version 1.0
 * @ClassName Test1Filter
 * @description: gateway
 * @date 2023/12/9 22:46
 */

public class Test1Filter implements GlobalFilter {

    private final static Logger LOG = LoggerFactory.getLogger(GlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOG.info("Test1Filter");
        return chain.filter(exchange);
    }
}
