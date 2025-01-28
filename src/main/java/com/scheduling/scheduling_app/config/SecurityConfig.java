package com.scheduling.scheduling_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desativa CSRF para simplificar durante o desenvolvimento
                .authorizeRequests()
                .anyRequest().permitAll(); // Permite todas as requisições sem autenticação
        return http.build();
    }
}
