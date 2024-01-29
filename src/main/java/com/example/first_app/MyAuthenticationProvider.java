package com.example.first_app;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider { //customizacion del AuthenticationProvider para prescindir de userDetailsService() y de password encoder en MySecurityConfig


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String userName = authentication.getName();
       String password = authentication.getCredentials().toString();
       if("tom".equals(userName)&&"cruise".equals(password)){
           return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList()); //Arrays.asList() es para pasar permisos, que aun no tenemos
       }else
       throw new BadCredentialsException("Invalid Username or Password");
    }

    //"So at runtime, the authentication manager passes this type within this authentication object to see
    //if this provider supports this type of authentication and we are comparing it with username password
    //authentication, token type.
    //If they both are same yes, this provider supports the username , and password authentication token type,
    //you can use him.That is what we are telling that authentication manager.
    //The job of authentication manager is to go through all the providers that are available that can support
    //the basic authentication or the username password authentication, whichever says that it can do it
    //first.
    //It will use it."
    @Override
    public boolean supports(Class<?> authentication) { //este metodo es para que se pueda devolver un tipo UsernamePasswordAuthenticationToken en vez de un tipo Authentication en el metodo anterior
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
