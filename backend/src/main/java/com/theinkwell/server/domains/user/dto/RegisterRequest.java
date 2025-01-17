package com.theinkwell.server.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Imutable DTO that models the body inside a Register Request.
 */
@AllArgsConstructor @Getter
public final class RegisterRequest {

    private final String firstName;
    private final String email;
    private final String password;
    
}
