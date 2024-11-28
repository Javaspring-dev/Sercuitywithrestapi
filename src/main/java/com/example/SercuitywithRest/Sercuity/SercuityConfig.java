package com.example.SercuitywithRest.Sercuity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SercuityConfig {
    @Bean
    public SercuityField sercuityField(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(auth ->auth
                .requestMatchers("/api/users/**").hasRole("ADMIN")
                .requestMatchers("/api/users","/api/users/{id}").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
        )
                .httpBasic();
        return http.build();
    }


}
