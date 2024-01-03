package com.revature.demo.config;

import com.revature.demo.security.JwtAuthenticationEntryPoint;
import com.revature.demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // configure
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable()).authorizeHttpRequests(
                        auth->
                                auth.requestMatchers("/home/**").authenticated()
                                        .requestMatchers("/auth/login").permitAll()
                                        .anyRequest().authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))   // if any exception occers this will run
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // statless menas we are not storing any thing on server

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
