package com.theinkwell.server.domains.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {  
      
    /**
     * Defines a custom security filter chain to be applied before http requests.
     */
    @Bean
    SecurityFilterChain createCustomSecurityFilterChain(HttpSecurity http) throws Exception {

        String urlPath = "/api/v1";

        http.authorizeHttpRequests(requests -> requests.requestMatchers(urlPath + "/auth/register").permitAll()
                                                       .requestMatchers(urlPath + "/auth/login").permitAll()
                                                       .requestMatchers("/h2-console/**").permitAll()
                                                       .requestMatchers(urlPath + "/products/**").permitAll()
                                                       .requestMatchers(urlPath + "/admin/**").hasRole("ADMIN")
                                                       .requestMatchers(urlPath + "/customer/**").hasRole("CUSTOMER")
                                                       .anyRequest().authenticated()
        );

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // allows the h2-console frames to be displayed
        http.csrf(csrf -> csrf.disable()); // removes the authentication form for h2 access

        // this makes the authentication STATELESS, setting up the jwt feature
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       
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
