package eafcanghel.pcshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/",
            "/pc-shop"
    };
    public static final String ROOT_URL = "/pc-shop";
//    public static final String LOGOUT_URL = "/logout";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request ->
                request.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .csrf().disable()
                .formLogin().disable();
        return http.build();
    }
}
