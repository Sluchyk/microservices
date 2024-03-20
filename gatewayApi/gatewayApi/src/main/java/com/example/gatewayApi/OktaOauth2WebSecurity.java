package com.example.gatewayApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class OktaOauth2WebSecurity {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity){
       return  httpSecurity.
                authorizeExchange(v->v.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oAuth -> oAuth.jwt(Customizer.withDefaults())).build();
    }

}
