package com.example.demo.config;

import com.example.demo.security.JwtAuth;
import com.example.demo.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuth jwtAuth;

    public SecurityConfig(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Use the explicit Bean below
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/Cinemas/**").permitAll()
    // STRICT ADMIN LOCKDOWN:
            .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
    // If you have employee-specific endpoints:
            .requestMatchers("/api/employee/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
    // Everything else requires at least being logged in
            .anyRequest().authenticated()
)
            .addFilterBefore(new JwtFilter(jwtAuth), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
	//configuration.setAllowedOrigins(List.of("http://localhost:5500", "http://127.0.0.1:5500", "http://100.49.29.15:5500"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
