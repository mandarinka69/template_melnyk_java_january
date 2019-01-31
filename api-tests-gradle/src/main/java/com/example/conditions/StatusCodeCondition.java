package com.example.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatusCodeCondition implements Condition {

    private final Integer expecterStatusCode;

    @Override
    public void  check(ValidatableResponse response) {
        response.assertThat().statusCode(expecterStatusCode);
    }

    @Override
    public String toString() {
        return "status code is "+expecterStatusCode+"";
    }
}
