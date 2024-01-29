package com.example.first_app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig { //customizacion del Authorization Provider

/*    @Bean
    UserDetailsService userDetailsService(){ //customizacion del UserDetailsService
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("tom").password(passwordEncoder().encode("cruise")). //aca generamos un usuario y lo encriptamos
                authorities("read").build(); //aca le damos permiso de lectura
        userDetailsService.createUser(user); //se lo pasamos al servicio
        return userDetailsService;
    }*/

    @Bean
    BCryptPasswordEncoder passwordEncoder(){ //mandatorio para encriptar en el metodo userDetailsService()
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.httpBasic(); //Basic Auth, el ide lo marca como deprecado, pero corre igual!! Antes Basic Auth estaba por defecto, mientras q aca le estamos diciendo explicitamente q ocupe Basic Auth
        http.authorizeRequests().anyRequest().authenticated(); //cualquier peticion siempre que este autenticado con user y password de Basic Auth
        return http.build();
    }
}
