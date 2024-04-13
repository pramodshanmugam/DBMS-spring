package com.example.artworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API development
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless session management
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.OPTIONS).permitAll() // Allow preflight CORS requests
                        .anyRequest().permitAll() // This line can be adjusted based on your auth requirements
                )
                .cors(Customizer.withDefaults()); // Ensure default CORS configuration is applied

        return http.build();
    }
}
