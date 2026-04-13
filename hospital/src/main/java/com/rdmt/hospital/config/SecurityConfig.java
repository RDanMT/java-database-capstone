package com.rdmt.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para facilitar pruebas con H2 y API
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permitimos todas las peticiones temporalmente
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Permitir H2-Console
        
        return http.build();
    }
}
