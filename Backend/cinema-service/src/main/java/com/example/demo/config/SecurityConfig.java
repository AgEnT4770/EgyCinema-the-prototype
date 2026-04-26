package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JwtAuth;
import com.example.demo.security.JwtFilter;

@Configuration // Tells Spring this class contains configuration beans
@EnableWebSecurity // Enables Spring Security for the application
public class SecurityConfig {

    private final JwtAuth jwtAuth;

    public SecurityConfig(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth; // Injects JwtAuth so it can be passed to JwtFilter
        System.out.println(">>>SecurityConfig Loaded!");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(); // Overrides Spring's auto-generated UserDetailsService so it stops
                                                 // generating a default password
    }

    @Bean // Defines a bean for the security filter chain, configuring how Spring Security
          // should handle authentication and authorization
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new org.springframework.web.cors.CorsConfiguration();
                    config.setAllowedOrigins(java.util.List.of("http://127.0.0.1:5500"));
                    config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(java.util.List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection because we are using JWTs, which are not
                                              // vulnerable to CSRF attacks
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Register and login are public — no token needed
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN") // Admin endpoints — ROLE_ADMIN
                                                                                     // only
                        .anyRequest().hasAuthority("ROLE_USER") // All other endpoints require at least ROLE_USER
                )
                .addFilterBefore(new JwtFilter(jwtAuth), // Registers JwtFilter to run before Spring's default auth
                                                         // filter
                        UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Builds and returns the configured SecurityFilterChain bean
    }
}