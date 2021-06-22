package com.mortgert.security.authentication;

import com.mortgert.data.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CustomAuthenticationProvider implements AuthenticationProvider {


    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(shouldAuthenticateAgainstDataBase(name,password)){
            return new UsernamePasswordAuthenticationToken(name,password,new ArrayList<>());
        }else{
            throw new AuthenticationCredentialsNotFoundException("Credentials not Found, Please Try Again");
        }
    }

    private boolean shouldAuthenticateAgainstDataBase(String name, String password) {
        return userRepository.existsUserByUsernameAndPassword(name, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
