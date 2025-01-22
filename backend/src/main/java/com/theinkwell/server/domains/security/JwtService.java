package com.theinkwell.server.domains.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.theinkwell.server.domains.security.exceptions.BlackListedTokenException;

/**
 * Service that perfoms various JWT operatios, such as token generation,
 * parsing and validation.
 */
@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    /** This is the list where tokens that are invalid by the user, but have not yet expired, are store. */
    private static final List<String> blackListedTokens = new ArrayList<>();

    /** JDK implementation of the ScheduledExecutorService interface, used to schedule token removal from the blacklist */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    @Value("${inkwell.server.jwt.token-duration}")
    private long tokenDurationMiliseconds;

    @Value("${inkwell.server.jwt.secret-key}")
    private String jwtSecret;

    /** 
     * <p>Generates a valid <code>JWT token</code> for a specific user.</p>
     * <p>The token </p>   
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
     * <p>Method that takes an http request and attemps to retrive the token inside the
     * authorization header. An instance of <code>DecodedJWT</code> provides methods for retriving
     * useful information, such as claims, dates and metadata.</p>
     * 
     * <p>This method acts exactly like a jwt validator, that is, it will return a DecodedJwt instance
     * if a token is valid or an exception will be throw otherwise.</p>
     * 
     * @param token the jwt token
     * @return the decoded jwt token
     * @throws JWTVerificationException if the token is invalid or if one was not provided
     * @throws BlackListedTokenException if the token is blacklisted
     */
    public DecodedJWT decodeJWT(String token) {

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        DecodedJWT decodedJWT;

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaimPresence("email")
                    .withClaimPresence("role")
                    .withIssuer("inkwell")
                    .build();

            decodedJWT = verifier.verify(token);
            isTokenBlackListed(token);
            return decodedJWT;  
        }
        catch (JWTVerificationException | BlackListedTokenException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /** Returns a email registed as a claim inside the jwt token */
    public String getUserEmailFromToken(String token){
        return decodeJWT(token).getClaim("email").asString();
    }

    /**
     * Searches blackListedTokens and tries to find the token's string inside of it
     * 
     * @param token the jwt token String
     * @return false if the token IS NOT inside the list
     * @throws BlackListedTokenException if the token IS inside the list
     */
    public boolean isTokenBlackListed(String token) {
        for (int i = 0; i < blackListedTokens.size(); i++) {
            if (blackListedTokens.get(i).equals(token)) {
                throw new BlackListedTokenException("Token is blacklisted.");
            }
        }
        return false;
    }

    /**
     * <p>This method adds a token to the blacklist. Immediately after a <code>scheduleTask</code> is created,
     * so after the token expires it can be safely removed from the list.</p>
     * 
     * @param request the http request
     */
    public void addTokenToBlackList(String token) {
        DecodedJWT decodedJWT = decodeJWT(token);
        long removalTime = decodedJWT.getExpiresAt().getTime()/1000 - Instant.now().getEpochSecond();
        
        blackListedTokens.add(token);
        logger.info("Token: " + token + " was added to the blacklist. Remove in: " + removalTime + " seconds");

        // Now to set up the scheduled removal
        Runnable removeToken = () -> {
            blackListedTokens.remove(token);
            logger.info("Token: " + token + " is was removed from blacklist.");
        };
        scheduler.schedule(removeToken, removalTime, TimeUnit.SECONDS);
    }

}
