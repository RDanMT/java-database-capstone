package com.rdmt.hospital.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        // Crea una llave segura a partir del string secreto
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día de validez
                .signWith(getSigningKey()) // Usa la llave directamente
                .compact();
    }
}
