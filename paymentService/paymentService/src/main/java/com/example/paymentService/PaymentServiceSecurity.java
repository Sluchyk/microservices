package com.example.paymentService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class PaymentServiceSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request->request
                .requestMatchers("/api/v1/payment/**")
                .hasAuthority("SCOPE_internal")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth-> oauth.jwt(Customizer.withDefaults()))
                .build();
    }
}
