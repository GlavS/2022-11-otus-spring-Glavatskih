package ru.glavs.hw013.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] adminEndpoints = {
                "/book/edit",
                "/book/create",
                "/book/delete",
                "/book/create-new",
                "/author/**",
                "/genre/**"
        };

        String[] userEndpoints = {
                "/comment/**"
        };

        http
                .authorizeHttpRequests()
                    .mvcMatchers(adminEndpoints).hasRole("ADMIN") // TODO: error page
                    .mvcMatchers(userEndpoints).hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();
        return http.build();
    }
}
