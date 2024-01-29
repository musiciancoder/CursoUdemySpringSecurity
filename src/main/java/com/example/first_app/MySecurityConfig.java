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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
    //  http.formLogin();//formLogin se usa mas para aplicaciones web y tiene una linda presentacion por defecto en el browser.
       // http.authorizeRequests().anyRequest().authenticated(); //cualquier peticion siempre que este autenticado con user y password de Basic Auth
       http.authorizeHttpRequests().requestMatchers("/hello").authenticated();// una vez que el usuario ya se ha autenticadosolo puede ingresar a esta url
              //.anyRequest.denyAll(); //esto es para hacer explicito que las demas url estan prohibidas, pero es opcional, ademas el ide no me lo agarra a mi.
       http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);//que agregue el filtro que esta en MySecurityFilter() antes del filtro por defecto que viene en BasicAuthentication
        return http.build();
    }
}
