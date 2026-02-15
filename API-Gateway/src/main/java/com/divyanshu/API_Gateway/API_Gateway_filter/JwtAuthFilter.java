package com.divyanshu.API_Gateway.API_Gateway_filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    // Secret key same as user-service
    private final String secretKey = "mySuperSecureSecretKeyForJwtAuthenticationMicroserviceProject1234567890";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Allow requests for login/register without token
        if (path.contains("/auth")) {
            return chain.filter(exchange);
        }

        // Get token from header
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            // Parse JWT and validate signature
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Optional: you can print or check the email
            System.out.println("JWT is valid for user: " + claims.getSubject());

        } catch (Exception e) {
            // Token invalid or expired
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Token is valid, continue to forward request
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Filter order
    }
}
