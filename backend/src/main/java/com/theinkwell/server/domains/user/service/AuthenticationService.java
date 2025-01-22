package com.theinkwell.server.domains.user.service;

import java.time.Instant;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theinkwell.server.domains.security.JwtService;
import com.theinkwell.server.domains.security.exceptions.CustomAuhenticationException;
import com.theinkwell.server.domains.security.exceptions.UserAlreadyRegisteredException;
import com.theinkwell.server.domains.user.dto.LoginRequest;
import com.theinkwell.server.domains.user.dto.LoginResponse;
import com.theinkwell.server.domains.user.dto.RegisterRequest;
import com.theinkwell.server.domains.user.model.Customer;
import com.theinkwell.server.domains.user.model.Person;
import com.theinkwell.server.domains.user.repository.PersonRepository;
import com.theinkwell.server.domains.user.repository.RoleRepository;

@Service
public class AuthenticationService implements UserDetailsService{

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public AuthenticationService(PersonRepository personRepository, PasswordEncoder passwordEncoder,
                                 RoleRepository roleRepository, JwtService jwtService){
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    /**
     * Overrides the UserDetailsService's loadUserByUsername() method
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return personRepository.getUserCredentialsByEmail(username);
    }

    /**
     * <p>Persists a new user if the provided credentials are valid.</p>
     * <p>By default a user will receive the <code>Customer Role</code></p>
     * 
     * @param request DTO send as a part of a body's request
     * @throws UserAlreadyRegisteredException if this email specified is already in the database
     */
    public void registerNewUser(RegisterRequest request) {

        if (loadUserByUsername(request.getEmail()) == null) {   

            Customer newUser = new Customer();
            newUser.setFirstName(request.getFirstName());      
            newUser.setEmail(request.getEmail());   
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setCreatedAt(Instant.now());
            newUser.setRole(roleRepository.findById(2).get()); // id 2 corresponds to the customer role

            personRepository.save((Person)newUser);

        } else {
            throw new UserAlreadyRegisteredException("User already registered");
        }
        
    }

    /**
     * Method that takes a DTO and attemps to process it by creating a JWT token.
     * 
     * @param dto the user credentials
     * @return a dto containing a jwt token
     * 
     * @throws CustomAuhenticationException if the email does not exist or if the passwords do not match
     */
    public LoginResponse logUserAndReturnToken(LoginRequest dto){
        
        UserDetails user = loadUserByUsername(dto.getEmail());

        if(user == null) {
            throw new CustomAuhenticationException("Email does not exist.");
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomAuhenticationException("Password does not match");
        }

        return  new LoginResponse(jwtService.generateJwtToken(user));
    }


    /**
     * Calls the JwtService to perform a logout operation
     * @param token the jwt token
     */
    public void logoutUser(String token){
        jwtService.addTokenToBlackList(token);
    }
    
}
