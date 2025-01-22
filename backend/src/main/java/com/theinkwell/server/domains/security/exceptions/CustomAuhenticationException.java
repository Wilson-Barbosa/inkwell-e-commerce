package com.theinkwell.server.domains.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when a user's request for a protected/secured resource
 * is not authenticated properly.
 */
public class CustomAuhenticationException extends AuthenticationException {
    
    public CustomAuhenticationException(String message) {
        super(message);
    }

}
