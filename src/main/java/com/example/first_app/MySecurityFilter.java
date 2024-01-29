package com.example.first_app;

import jakarta.servlet.*;

import java.io.IOException;


public class MySecurityFilter  implements Filter { //customizacion de Authentication Filter

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before"); //al mandar una peticion va a aparecer "Before" q significa q esto se ejecuta antes del filtro
        chain.doFilter(request, response);//pasa request y response al siguiente eslabon de la cadena de filtros
        System.out.println("After"); //al mandar una peticion va a aparecer "After" q significa q esto se ejecuta despues del filtro
    }
}
