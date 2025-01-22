package com.theinkwell.server.domains.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.theinkwell.server.domains.security.exceptions.AuthenticationExceptionHandler;
import com.theinkwell.server.domains.security.exceptions.AuthorizationExeceptionHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
      
    /**
     * Defines a custom security filter chain to be applied before http requests.
     */
    @Bean
    SecurityFilterChain createCustomSecurityFilterChain(HttpSecurity http, AuthJwtFilter authJwtFilter,
            AuthenticationExceptionHandler authenticationHandler, AuthorizationExeceptionHandler authorizationHandler) throws Exception {

        String urlPath = "/api/v1";

        http.authorizeHttpRequests(requests -> requests.requestMatchers(urlPath + "/auth/register").permitAll()
                                                       .requestMatchers(urlPath + "/auth/login").permitAll()
                                                       .requestMatchers("/h2-console/**").permitAll()
                                                       .requestMatchers(urlPath + "/products/**").permitAll()
                                                       .requestMatchers(urlPath + "/admins/**").hasAuthority("ADMIN")
                                                       .requestMatchers(urlPath + "/customers/**").hasAuthority("CUSTOMER")
                                                       .anyRequest().authenticated()
        );

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // allows the h2-console frames to be displayed
        http.csrf(csrf -> csrf.disable()); // removes the authentication form for h2 access

        // this makes the authentication STATELESS, setting up the jwt feature
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // adds the jwt filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authJwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(execeptionHandling -> execeptionHandling.authenticationEntryPoint(authenticationHandler));
        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(authorizationHandler));
        return http.build();
    }


    /**
     * Returns an instance of a PasswordEnconder, specifically a BCrypt implementation.
     */
    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
