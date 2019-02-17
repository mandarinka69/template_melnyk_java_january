package com.example.conditions;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public interface Condition {
    void check(Response response);
}
