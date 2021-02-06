package pl.kabacinsp.obywatel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

public class SecurityConfiguration {

    private final long MAX_AGE_SECS = 3600;

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/home").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login()
                        .and()
                .formLogin()
                        .loginPage("/login")
                        .and()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setMaxAge(MAX_AGE_SECS);
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOriginPatterns(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
