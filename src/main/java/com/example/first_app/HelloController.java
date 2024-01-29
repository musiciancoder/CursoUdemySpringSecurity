package com.example.first_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Spring security rocks!";
    }

    @GetMapping("/bye")
    public String bye(){
        return "Get lost";
    }

}
