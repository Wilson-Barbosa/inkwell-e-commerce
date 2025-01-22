package com.theinkwell.server.domains.security.exceptions;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theinkwell.server.domains.utils.WebResponseExceptionBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationExeceptionHandler implements AccessDeniedHandler  {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
        
                WebResponseExceptionBody responseBody = new WebResponseExceptionBody();
                responseBody.setMessage(accessDeniedException.getMessage());
                responseBody.setTime(Instant.now());
                responseBody.setPath(request.getRequestURI());
        
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(responseBody));
    }
}
