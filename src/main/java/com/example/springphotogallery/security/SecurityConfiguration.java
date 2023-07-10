package com.example.springphotogallery.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    DatabaseUserDetailServices userDetailServices() {
        return new DatabaseUserDetailServices();
    }

    // Questo è il PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        // Creo l'authenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Gli setto il PasswordEncoder
        provider.setPasswordEncoder(passwordEncoder());
        // Gli setto UserDetailServices
        provider.setUserDetailsService(userDetailServices());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Definisco la catena di filtri
        http.authorizeHttpRequests()
                .requestMatchers("/categories").hasAnyAuthority("ADMIN")
                .requestMatchers("/photos/edit/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/photos/create").hasAnyAuthority("ADMIN")
                .requestMatchers("/photos/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/categories/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categories/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        // Disabilitazione csrf per poter invocare le api da Postman
        http.csrf().disable();
        return http.build();
    }
}
