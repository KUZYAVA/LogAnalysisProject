package ru.Kuzevanov_Alexander.NauJava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration class. This class configures Spring Security to protect
 * application URLs based on user roles and provides a bean for password encoding.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Creates a bean for password encoding using BCrypt.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain. This method defines the authorization rules for
     * different URLs based on user roles.  It also configures default form-based login.
     *
     * @param http The HttpSecurity object to configure.
     * @return A SecurityFilterChain instance.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration", "/login", "/logout")
                        .permitAll()
                        .requestMatchers("/admin").hasRole(Constants.ROLE_ADMIN)
                        .requestMatchers("/swagger-ui/index.html").hasRole(Constants.ROLE_ADMIN)
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
