package ticketguru.demo;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
        .cors().and()
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            // Julkiset API-pyynnöt
            .requestMatchers(HttpMethod.POST, "/api/ticketsales").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/events").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/tickettypes").permitAll()

            // Admin-only API
            .requestMatchers("/api/users/**", "/api/roles").hasRole("ADMIN")

            // Julkiset sivut
            .requestMatchers("/", "/status", "/css/**", "/js/**").permitAll()

            // Index vaatii kirjautumisen (voit halutessasi rajata vain ADMINille)
            .requestMatchers("/").authenticated()

            // Kaikki muut vaativat kirjautumisen
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")                // Thymeleaf login.html
            .defaultSuccessUrl("/", true)  // onnistuneen loginin jälkeen index
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

    return http.build();
}



    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
