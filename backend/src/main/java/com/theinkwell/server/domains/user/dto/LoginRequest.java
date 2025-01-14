package com.theinkwell.server.domains.user.dto;

/**
 * Immutable DTO that models a Login resquest
 */
public final class LoginRequest {
    
    private final String email;
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
