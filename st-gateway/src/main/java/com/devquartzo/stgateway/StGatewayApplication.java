package com.devquartzo.stgateway;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.IOException;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@Log
public class StGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StGatewayApplication.class, args);
    }

    @Bean
    public Docket api() throws IOException {

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/api")
                .select()
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sound Tracker API").version("1.0.0").build();
    }
}
