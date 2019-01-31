package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import io.qameta.allure.Step;

public class UserApiService extends ApiService{

    @Step
    public AssertableResponse registerUser (UserPayload userPayload){
        return new AssertableResponse( setup()
                .body(userPayload)
                .when()
                .post("register")
                .then());
    }

}
