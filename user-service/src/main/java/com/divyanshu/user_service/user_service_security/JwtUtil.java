package com.divyanshu.user_service.user_service_security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    private final String secretKey ="mySuperSecureSecretKeyForJwtAuthenticationMicroserviceProject1234567890";

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token){
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token , String email){
        String extractedEmail =getEmailFromToken(token);

        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Date date=getClaims(token).getExpiration();

        return date.before(new Date());
    }



    // get claims is use for getting token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // Use same secret key to verify token
                .build()
                .parseClaimsJws(token)  // Parse token
                .getBody();  // Get token body (claims)
    }

}
