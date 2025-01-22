package com.theinkwell.server.domains.security.exceptions;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.theinkwell.server.domains.utils.WebResponseExceptionBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>Class that will handle any AuthenticationExceptions throw by the application. I need to override
 * <code>commence()</code> from the <code>AuthenticationEntryPoint</code> interface and then registry it
 * inside my SecurityConfiguration, so it can be sent as a proper HttpResponse's body.</p>
 * <p>Note that even though the AuthenticationException will be thrown they will not be sent as a part
 * of the response using regular <code>ExceptionHandlers</code>, so the implementation is therefore required.</p>  
 */
@Component
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint  {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationExceptionHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        logger.info("\n\nAuthenticationExceptionHandler called\n\n");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        
        WebResponseExceptionBody responseBody = new WebResponseExceptionBody();
        responseBody.setMessage("error appears");
        responseBody.setTime(Instant.now());
        responseBody.setPath(request.getRequestURI());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        logger.info(mapper.writeValueAsString(new WebResponseExceptionBody()));

        response.getWriter().write(mapper.writeValueAsString(responseBody));
        
    }
    
}
