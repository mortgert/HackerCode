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


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;




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
        String token = JWT.create().withSubject(((User)auth.getPrincipal()).getUsername())
                                    .withExpiresAt(Date.from(Instant.now().plusMillis(EXPIRATION_TIME)))
                                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
        String body = ((User)auth.getPrincipal()).getUsername()+ " " + token;
        response.getWriter().write(body);
    response.getWriter().flush();
    }
}

