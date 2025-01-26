package com.rakesh.apigateway.filter;

import com.rakesh.apigateway.util.JwtUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("Incoming request: {} {}", exchange.getRequest().getMethod().name(), exchange.getRequest().getPath());
            if (validator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(config.getHeaderName())) {
                    throw new RuntimeException("missing authorization header");
                }

                try {
                    String accessToken = exchange.getRequest().getHeaders().get(config.getHeaderName()).stream()
                            .findFirst()
                            .orElseThrow(() -> new BadCredentialsException("Missing Token"));
                    boolean isTokenValid = jwtUtil.validateToken(accessToken.substring(7));
                    if(isTokenValid) {
                        String userName = jwtUtil.getUsernameFromToken(accessToken.substring(7));
                        exchange.getAttributes().put("userName", userName);
                        return chain.filter(exchange);
                    } else {
                        log.info("invalid token...!");
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    }
                } catch (Exception e) {
                    log.info("invalid access...!");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        });
    }

    @Getter
    @Setter
    @Builder
    public static class Config {
        // HttpHeaders.AUTHORIZATION
        private String headerName;
    }

}
