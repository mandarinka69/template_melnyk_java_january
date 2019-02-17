package com.example.conditions;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

    private final Integer expecterStatusCode;

    @Override
    public void  check(Response response) {
        response.then().assertThat().statusCode(expecterStatusCode);
    }

    @Override
    public String toString() {
        return "Expected status code is "+expecterStatusCode+"";
    }
}
