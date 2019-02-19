package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.CreateNotification;
import com.example.model.UserLoginFromMobile;
import com.example.model.UserPayload;
import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserApiService extends ApiService{

    @Step
    public AssertableResponse registerUser (UserPayload userPayload){
//        log.info("About to create new user " + userPayload);
        return new AssertableResponse( setup()
                .body(userPayload)
                .when()
                .post("register"));
    }

    @Step
    public AssertableResponse deleteUser(String id){
        return new AssertableResponse(setup()
                .when()
                .delete("customer/{id}", id));
    }

    @Step
    public AssertableResponse getUserById(String id){
        return new AssertableResponse(setup()
                .when()
                .get("customers/{id}", id));
    }

    @Step
    public AssertableResponse getAllUsers(){
        return new AssertableResponse(setup()
                .when()
                .get("customers"));
    }

    @Step
    public AssertableResponse sendPost(UserLoginFromMobile postBody, String postResponce){
        return new AssertableResponse(setup()
                .body(postBody)
                .when()
                .post(postResponce));
    }

    @Step
    public AssertableResponse sendPost(CreateNotification postBody, String postResponce, Cookies cookies){
        return new AssertableResponse(setup()

                .body(postBody)
                .cookies(cookies)
                .when()
                .post(postResponce));
    }

    @Step
    public AssertableResponse sendPost(CreateNotification postBody, String postResponce, Map<String, ?> cookies){
        return new AssertableResponse(setup()

                .body(postBody)
                .cookies(cookies)
                .when()
                .post(postResponce));
    }

    @Step
    public AssertableResponse sendGet(String postResponce, Map<String, ?> cookies){
        return new AssertableResponse(setup()

                .cookies(cookies)
                .when()
                .get(postResponce));
    }

    public AssertableResponse sendDelete(String deleteResponce, Map<String, ?> cookies){

        return new AssertableResponse(setup()
                .cookies(cookies)
                .when()
                .delete(deleteResponce));
    }

}
