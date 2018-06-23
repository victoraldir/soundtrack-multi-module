package com.devquartzo.stgateway;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@Log
public class StGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StGatewayApplication.class, args);
    }

}
