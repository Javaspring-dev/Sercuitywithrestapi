package com.example.SercuitywithRest.Sercuity;

import com.example.SercuitywithRest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SercuityConfig {
    @Autowired
    private final UserService userService;
    public SercuityConfig(UserService userService){
        this.userService = userService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer = http.csrf().disable().authorizeHttpRequests(auth -> auth
                        .requestMatchers("/create", "/login").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN"))
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/login");
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
