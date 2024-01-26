package com.example.first_app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig { //customizacion del Authorization Provider

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.httpBasic(); //Basic Auth, el ide lo marca como deprecado, pero corre igual!! Antes Basic Auth estaba por defecto, mientras q aca le estamos diciendo explicitamente q ocupe Basic Auth
        http.authorizeRequests().anyRequest().authenticated(); //cualquier peticion siempre que este autenticado con user y password de Basic Auth
        return http.build();
    }
}
