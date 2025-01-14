package com.theinkwell.server.domains.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.theinkwell.server.domains.user.exception.AuthenticationException;
import com.theinkwell.server.domains.user.service.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom Filter that sits inside spring's filter chain. This filter will make sure a jwt token's
 * validity for a logged user's request. 
 */
@Component
public class AuthJwtFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthJwtFilter(AuthenticationService authenticationService, JwtService jwtService){
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // Get the request path
        String path = request.getRequestURI();

        // Skip JWT validation for public endpoints
        if (path.startsWith("/api/v1/admin/") || path.startsWith("/api/v1/customer")) {

            try {
            
                String email = jwtService.isTokenValid(request);
                UserDetails userDetails = authenticationService.loadUserByUsername(email);
    
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    
            } catch (JWTVerificationException e) {
                throw new AuthenticationException("Jwt Token provided is invalid.");
            }
    
            // After the token is validated the application continues with the filter chain
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
        return;
                
    }
    
}
