package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.security.JwtAuth;
import com.example.demo.security.JwtFilter;

import java.util.List;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {

    private final JwtAuth jwtAuth;

    public SecurityConfig(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth;
        System.out.println(">>> SecurityConfig Loaded with updated Cinema permissions!");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Prevents Spring from generating a default "user" password in the console
        return new InMemoryUserDetailsManager(); 
    }

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Updated CORS to include both common Live Server origins
                // Inside your SecurityConfig.java filterChain
.cors(cors -> cors.configurationSource(request -> {
    var config = new org.springframework.web.cors.CorsConfiguration();
    config.setAllowedOrigins(java.util.List.of("http://127.0.0.1:5500", "http://localhost:5500"));
    config.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(java.util.List.of("*"));
    config.setAllowCredentials(true); 
    return config;
}))
                
                // 2. Disable CSRF for REST APIs using JWT
                .csrf(csrf -> csrf.disable()) 

                // 3. Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints: No token required
                        .requestMatchers("/api/auth/**").permitAll() 
                        .requestMatchers("/api/Cinemas/**").permitAll() // 👈 Added: Allows home.html to load cinemas
                        
                        // Protected endpoints: Role-based
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN") 
                        
                        // All other actions (like booking) require a login
                        .anyRequest().hasAuthority("ROLE_USER") 
                )
                
                // 4. JWT Filter Integration
                .addFilterBefore(new JwtFilter(jwtAuth), UsernamePasswordAuthenticationFilter.class);

        return http.build(); 
    }
}