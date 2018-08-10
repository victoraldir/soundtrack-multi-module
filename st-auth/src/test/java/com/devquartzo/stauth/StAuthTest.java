package com.devquartzo.stauth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Ignore
public class StAuthTest {


    @Test
    public void shouldPass(){
        String accessToken = obtainAccessToken("spring-security-oauth2-read-write-client", "admin", "admin1234");
        Assert.assertNotNull(accessToken);
    }

    private String obtainAccessToken(String clientId, String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);

        Response response = RestAssured.given().auth().
                preemptive().basic(clientId,"secret").and().with().params(params).
                when().post("http://localhost:8089/oauth/token");

        return response.jsonPath().getString("access_token");
    }

}