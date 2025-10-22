package ticketguru.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;


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
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
              // GET is public
              .requestMatchers(HttpMethod.GET, "/api/tickettypes").permitAll()

              // POST, PUT, DELETE require authentication
              .requestMatchers(HttpMethod.POST, "/api/tickettypes").authenticated()
              .requestMatchers(HttpMethod.PUT, "/api/tickettypes/**").authenticated()
              .requestMatchers(HttpMethod.DELETE, "/api/tickettypes/**").authenticated()

            .requestMatchers("/api/users/**", "/api/roles").hasRole("ADMIN") // admin only
            .anyRequest().authenticated()                         // all others require login
        )
        .httpBasic();
    return http.build();
}

}
