package com.Book_Mart.OOP.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySuperSecureSecretKeyForJwtToken123456"; // At least 32 characters
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }


    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
}
