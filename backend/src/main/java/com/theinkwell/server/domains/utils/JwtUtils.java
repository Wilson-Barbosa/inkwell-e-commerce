package com.theinkwell.server.domains.utils;

import com.theinkwell.server.domains.security.exceptions.CustomAuhenticationException;

public class JwtUtils {
    
    /**
     * Helper method that takes a string as an authorization header and tries to extract a token from it
     * 
     * <p>The header must have the following value: <code>Bearer your-token-string</code></p>
     * 
     * @param authorizationHeader a header extracted from an http request
     * @return the jwt token 
     */
    public static String extractTokenFromHeader(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Extract the token
        } else {
            throw new CustomAuhenticationException("JWT token is not present or the Authorization header is not formatted properly");
        }
    }

}
