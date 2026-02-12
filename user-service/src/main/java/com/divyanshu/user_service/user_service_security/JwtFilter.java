package com.divyanshu.user_service.user_service_security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil util;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader=request.getHeader("Authorization");

        String token=null;
        String email=null;;

        if (authHeader!=null && authHeader.startsWith("Berber ")){
            token=authHeader.substring(7);

            try {
                email=util.getEmailFromToken(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT Token");
            }
        }
        if (email!=null && util.validateToken(token,email)){
            System.out.println("Jwt Token is valid for user: "+email);
        }else System.out.println("JWT is not valid");

        filterChain.doFilter(request, response);
    }
}
