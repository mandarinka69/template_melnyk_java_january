package com.example.conditions;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;


@RequiredArgsConstructor
public class BodyFieldCondition implements Condition{

    private final String jsonPath;
    private final Matcher matcher;

    public BodyFieldCondition(Matcher matcher) {
        this(null, matcher);
    }

    @Override
    public void check(Response response) {
        if (StringUtils.isBlank(jsonPath)) {
            response.then().assertThat().body(jsonPath, matcher);
        }else {
            response.then().assertThat().body(jsonPath, matcher);
        }
    }

    @Override
    public String toString() {
        if (StringUtils.isBlank(jsonPath)){
            return "body field " + matcher ;
        }
        return "body field ["+jsonPath  + "] "+matcher ;
    }
}
