package com.devquartzo.stauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class StAuth extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StAuth.class, args);
    }

}
