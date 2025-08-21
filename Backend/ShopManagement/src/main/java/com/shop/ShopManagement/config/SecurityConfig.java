package com.shop.ShopManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()   // allow login/register
                        .anyRequest().authenticated()              // everything else needs JWT
                )
                .formLogin(form -> form.disable())   // 🚨 disable Spring’s default login form
                .httpBasic(basic -> basic.disable()); // 🚨 disable basic auth

        return http.build();
    }
}
