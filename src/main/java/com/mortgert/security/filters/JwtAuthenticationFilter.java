package com.mortgert.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgert.data.dtos.Credentials;
import com.mortgert.data.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static com.mortgert.util.SecurityConstants.EXPIRATION_TIME;
import static com.mortgert.util.SecurityConstants.SECRET;


/**
 * The type Jwt authentication filter.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    /**
     * Instantiates a new Jwt authentication filter.
     *
     * @param manager the manager
     */
    public JwtAuthenticationFilter(AuthenticationManager manager){
        this.authenticationManager = manager;
        setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException{
        try{
            Credentials credentials = new ObjectMapper().readValue(request.getInputStream(),Credentials.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),credentials.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth)throws IOException{
        byte[] bytes = SECRET.getBytes();
         Algorithm alg = Algorithm.HMAC512(bytes);
        String token = JWT.create().withSubject((auth.getPrincipal().toString()))
                                    .withExpiresAt(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                                    .sign(alg);
        String body = ((User)auth.getPrincipal()).getUsername()+ " " + token;
        response.getWriter().write(body);
    response.getWriter().flush();
    }
}

