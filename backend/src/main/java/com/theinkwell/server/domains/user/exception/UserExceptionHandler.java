package com.theinkwell.server.domains.user.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.theinkwell.server.domains.security.exceptions.UserAlreadyRegisteredException;
import com.theinkwell.server.domains.utils.WebResponseExceptionBody;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<WebResponseExceptionBody> handleUserAlreadyRegisteredException(HttpServletRequest request,
                                                                                  Exception e){
        
        WebResponseExceptionBody responseBody = new WebResponseExceptionBody();
        responseBody.setMessage(e.getMessage());
        responseBody.setTime(Instant.now());
        responseBody.setPath(request.getRequestURI());        
                                                                                    
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }


}
