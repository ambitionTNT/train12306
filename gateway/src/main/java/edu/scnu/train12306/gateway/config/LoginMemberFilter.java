package edu.scnu.train12306.gateway.config;

import edu.scnu.train12306.gateway.utils.JwTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author long
 * @version 1.0
 * @ClassName LoginMemberFilter
 * @description: TODO
 * @date 2023/12/11 15:06
 */
@Component
public class LoginMemberFilter implements Ordered, GlobalFilter {
    private final static Logger LOG = LoggerFactory.getLogger(LoginMemberFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        //排除不需要拦截的请求
        if (path.contains("/admin")
                || path.contains("/hello")
                || path.contains("/member/member/sendCode")
                || path.contains("/member/member/login")){
            LOG.info("不需要登录验证:{}", path);
            return chain.filter(exchange);
        }else {
            LOG.info("需要登录验证:{}", path);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token==null || token.isEmpty()){
            LOG.info("token为空, 请求需要拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //校验token是否有效，包括token是否被修改过，是否过期
        boolean validate = JwTUtil.validate(token);
        if (validate){
            LOG.info("token有效, 放行该请求");
            return chain.filter(exchange);
        }else {
            LOG.warn("token无效, 请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
