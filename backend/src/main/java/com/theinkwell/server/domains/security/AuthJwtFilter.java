package com.theinkwell.server.domains.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.theinkwell.server.domains.security.exceptions.CustomAuhenticationException;
import com.theinkwell.server.domains.user.service.AuthenticationService;
import com.theinkwell.server.domains.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom Filter that sits inside spring's security filter chain. This filter will make
 * sure of a jwt token's validity for a logged user's request.
 */
@Component
public class AuthJwtFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthJwtFilter(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Perform jwt verification only for these endpoints
        if (path.startsWith("/api/v1/admins/") || path.startsWith("/api/v1/customers") || path.startsWith("/api/v1/auth/logout")) {
            try {
                String email = jwtService.getUserEmailFromToken(JwtUtils.extractTokenFromHeader(request.getHeader("Authorization")));
                UserDetails userDetails = authenticationService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            catch (Exception e) {
                // e.printStackTrace();
                throw new CustomAuhenticationException("Jwt Token provided is invalid.");
            }
        }

        filterChain.doFilter(request, response);
    }

}
