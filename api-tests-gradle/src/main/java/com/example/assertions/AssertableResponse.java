package com.example.assertions;

import com.example.conditions.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;

    @Step("api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition){
//        log.info("About to check condition [ " + condition+" ]");
        condition.check(response);
        return this;
    }

    public <T> T getValue(String jsonPath, Class<T> tClass){
        return response.jsonPath().getObject(jsonPath, tClass);
    }

    public String getValue(String jsonPath){
        return response.jsonPath().getObject(jsonPath, String.class);
    }

    public <T> T asPojo(Class <T> tClass){
        String inputStream = response.getBody().asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(inputStream, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        return response.as(tClass);
    }

    public Map<String, String> getCookies() {
        return response.getCookies();
    }

    public JsonPath jsonPath() {
       return response.jsonPath();
    }
}
