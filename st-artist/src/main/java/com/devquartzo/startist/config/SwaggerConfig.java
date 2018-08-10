package com.devquartzo.startist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTH_SERVER = "http://localhost:8089/oauth";
    public static final String CLIENT_ID = "spring-security-oauth2-read-write-client";
    public static final String CLIENT_SECRET = "spring-security-oauth2-read-write-client-password1234";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devquartzo.startist.controller"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Artist API").version("1.0.0").build();
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().clientId(CLIENT_ID).clientSecret(CLIENT_SECRET)
                .useBasicAuthenticationWithAccessCodeGrant(true).build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
                .tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER + "/authorize",
                        CLIENT_ID, CLIENT_SECRET))
                .build();

//        GrantType grantType = new ClientCredentialsGrant(AUTH_SERVER + "/token");

//        GrantType grantType = new ClientCredentialsGrant(AUTH_SERVER);

        SecurityScheme oauth = new OAuthBuilder()
                .name("Authorization")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(
                Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/secured/*"))
                .build();
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("foo", "Access foo API")};
        return scopes;
    }

}
