package ticketguru.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF for REST APIs
        http
            .cors().and()
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(authorize -> authorize
                // Public resources
                .requestMatchers("/login", "/status", "/css/**", "/js/**").permitAll()

                // Admin-only endpoints
                .requestMatchers("/api/users/**", "/api/roles/**").hasRole("ADMIN")

                // Other /api/** endpoints accessible by ADMIN or MYYJÄ
                .requestMatchers("/api/**").hasAnyRole("ADMIN", "MYYJÄ")

                // All other requests require authentication
                .anyRequest().authenticated()
            );

        // HTTP Basic authentication (for Postman / REST clients)
        http.httpBasic();

        // Form login for browser
        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        );

        // Logout configuration
        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );

        return http.build();
    }

    // CORS configuration for Spring Security
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // allow all origins
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
