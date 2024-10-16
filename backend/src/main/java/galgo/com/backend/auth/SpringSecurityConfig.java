package galgo.com.backend.auth;


import galgo.com.backend.auth.filters.JwtAuthenticationFilter;
import galgo.com.backend.auth.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


/**
 * Class for security
 */
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET,
                                "/restaurants/**",
                                "/users",
                                "/address",
                                "/restaurants/active"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/restaurants/**",
                                "/api/restaurants",
                                "/api/users",
                                "/api/address",
                                "/address/{restaurantId}/add-address",
                                "/users/confirm-account/{token}"
                        ).permitAll()
                        .requestMatchers(HttpMethod.PUT,
                                "/restaurants/{restaurantId}/activate",
                                "/restaurants/types/{id}"
                        ).permitAll()
                        .requestMatchers(HttpMethod.DELETE,
                                "/restaurants/types/{id}"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/restaurants/my-restaurant",
                                "/categories/active"
                        ).hasRole("BUSINESS")
                        .requestMatchers(HttpMethod.PUT,
                                "/restaurants/{restaurantId}/activate"
                        ).hasAnyRole("BUSINESS", "ADMIN")
                        .anyRequest().authenticated()
                )

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost/:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
