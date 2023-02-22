package com.jpcchaves.finances.security;

import com.jpcchaves.finances.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt-expiration-milliseconds}")
    private Long jwtExpirationMilliseconds;

    public Date generateExpirationDate (Long expiration) {
        return new Date(new Date().getTime() + expiration);
    }

    public Key generateKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        try {
            return Jwts
                    .builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(generateExpirationDate(jwtExpirationMilliseconds))
                    .signWith(generateKey(jwtSecret))
                    .compact();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private Claims getClaims(String token) {

        try {

            return Jwts
                    .parserBuilder()
                    .setSigningKey(generateKey(jwtSecret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private String getUserName(String token) {

        Claims claims = getClaims(token);

        if(claims == null) {
            return null;
        }

        return claims.getSubject();

    }
}
