package com.theinkwell.server.domains.user.dto;

/**
 * Imutable DTO that models the body inside a Register Request.
 */
public final class RegisterRequest {

    private final String email;
    private final String password;

    public RegisterRequest(String email, String password){
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
