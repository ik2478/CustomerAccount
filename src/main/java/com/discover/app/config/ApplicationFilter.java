package com.discover.app.config;

import com.discover.app.exception.DownStreamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ApplicationFilter implements WebFilter {
    @Order()
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        exchange.getResponse().getHeaders().add("x-ACCOUNT", "0000");
        // exchange.getResponse().getHeaders().add("x-des", "0001");
        // exchange.getRequest().getHeaders().add("aaa","sss");
        // exchange.getAttribute("X-B3-SpanId");
        log.info("word att: {}", exchange.getRequest().getPath());
        log.info("word att: {}", exchange.getRequest().getId());
        log.info("headers att: {}", exchange.getRequest().getHeaders());
        log.info("query params att: {}", exchange.getRequest().getQueryParams());
        try {
            if (exchange.getRequest().getHeaders().containsKey("x-dfs-w")) {

                throw new DownStreamException("request contains unwanted headers");
            }
        } catch (DownStreamException e) {
            log.info(e.getMessage());
        }

        // exchange.getRequest().getHeaders().add("x-Apply", "power");
        // serverHttpRequest.getHeaders().add("x-ACCOUNT","0000");

        return chain.filter(exchange);
    }
}
