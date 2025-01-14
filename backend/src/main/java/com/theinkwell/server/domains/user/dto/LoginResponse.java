package com.theinkwell.server.domains.user.dto;

/**
 * Immutable DTO that models a response containing a signed JWT.
 */
public final class LoginResponse {
    
    private final String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
