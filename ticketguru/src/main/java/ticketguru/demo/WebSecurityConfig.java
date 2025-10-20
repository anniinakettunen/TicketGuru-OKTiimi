package ticketguru.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher("/api/roles")).permitAll()
                        .requestMatchers(antMatcher("/api/ticketsales")).permitAll()
                        .requestMatchers(antMatcher("/api/tickettypes")).permitAll()
                        .requestMatchers(antMatcher("/api/users")).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
}