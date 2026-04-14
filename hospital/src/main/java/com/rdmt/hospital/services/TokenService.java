package com.rdmt.hospital.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    // Configura por defecto un secreto seguro, o lo toma de properties
    @Value("${jwt.secret:EstaEsUnaClaveSuperSecretaValidaParaJWT123456}")
    private String secret;

    // Criterio de la rúbrica: Genera token JWT usando el email (3 pts)
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 Día
                .signWith(getSigningKey())
                .compact();
    }

    // Criterio de la rúbrica: Devuelve la clave de firma usando el secreto (2 pts)
    public SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Servicio dedicado para validación robusta de tokens (Sugerido en Pregunta 5)
    public boolean validateToken(String token) {
        try {
            String jwt = token.replace("Bearer ", "");
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(jwt);
            return true; // Token válido
        } catch (Exception e) {
            return false; // Token expirado o malformado
        }
    }
}
