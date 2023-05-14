package com.project.rest.config;

import com.project.rest.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class SecurityWebConf {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http
                    .csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/register", "'api/login").permitAll()
                    .requestMatchers("/api/studenci/**").hasAuthority(Role.ADMIN.toString())
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()

                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
}
