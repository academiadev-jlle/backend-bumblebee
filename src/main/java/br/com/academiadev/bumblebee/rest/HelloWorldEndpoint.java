package br.com.academiadev.bumblebee.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldEndpoint {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world!";
    }

}
