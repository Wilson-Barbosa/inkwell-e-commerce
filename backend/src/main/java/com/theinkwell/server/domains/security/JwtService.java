package com.theinkwell.server.domains.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.theinkwell.server.domains.user.exception.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Service that perfoms various JWT operatios, such as token generation,
 * parsing and validation.
 */
@Service
public class JwtService {

    @Value("${inkwell.server.jwt.token-duration}")
    private int tokenDurationMiliseconds;

    @Value("${inkwell.server.jwt.secret-key}")
    private String jwtSecret;
    
    /** 
     * <p>Generates a <code>JWT token</code> for a specific user.</p>  
     * 
     * @param userDetails is the user credentials
     * @return a jwt token
     */
    public String generateJwtToken(UserDetails userDetails){ 

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Instant issueAt = Instant.now();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        String token = JWT.create()
                    .withClaim("email", userDetails.getUsername())
                    .withClaim("role", role)
                    .withIssuedAt(issueAt)
                    .withExpiresAt(issueAt.plusMillis(tokenDurationMiliseconds))
                    .withIssuer("inkwell")
                    .sign(algorithm);

        return token;
    }

    /** 
     * <p>Extracts an JWT token from a http request. The method looks for the <code>authorization</code> 
     * header and then tries to get only the token from it.</p>
     * 
     * @param request the http request coming from the user
     * @return the complete JWT token or null if the token is provided or if it's not
     * properly formated.
    */
    public String getJwtFromHeader(HttpServletRequest request){

        String bearerToken = request.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        } else {
            throw new AuthenticationException("JWT token is not present or the Authorization header is not formatted properly");
        }
    }

    /** 
     * Validates a token from a given http request and returns the email inside a Jwt token's claim.
     * 
     * @param request the http request containing an Authorization header
     * 
     * @return the user's email if the token is valid (i.e the signature is intact)
     * @throws JWTVerificationException if the token is invalid or if one was not provided
     */
    public String isTokenValid(HttpServletRequest request){

        String token = getJwtFromHeader(request);
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        DecodedJWT decodedJWT;

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withClaimPresence("email")
                                      .withClaimPresence("role")
                                      .withIssuer("inkwell")
                                      .build();
            
            decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("email").asString();

        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
