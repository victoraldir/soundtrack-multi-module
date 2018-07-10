package com.devquartzo.stauth;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.ws.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class StAuthTest {


    @Test
    public void shouldPass(){
        String accessToken = obtainAccessToken("fooClientIdPassword", "john", "123");
        Assert.assertNotNull(accessToken);
    }

    private String obtainAccessToken(String clientId, String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        io.restassured.response.Response response = RestAssured.given().auth().preemptive()
                .basic(clientId, "secret").and().with().params(params).when()
                .post("http://localhost:8089/oauth/token");
        return response.jsonPath().getString("access_token");
    }

}