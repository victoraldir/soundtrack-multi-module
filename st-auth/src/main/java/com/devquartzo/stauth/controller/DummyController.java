package com.devquartzo.stauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dummy")
public class DummyController {

    @RequestMapping("/hello")
    public String sayHi(){
        return "Hello user";
    }

}
