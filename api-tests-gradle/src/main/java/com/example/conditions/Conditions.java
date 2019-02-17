package com.example.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    public static StatusCodeCondition statusCode(int code){
        return new StatusCodeCondition(code);
    }

    public static BodyFieldCondition bodyField(String jsonPath, Matcher matcher){
        return new BodyFieldCondition(jsonPath, matcher);
    }

//    public static BodyFieldCondition bodyField(Integer jsonPath, Matcher matcher){
//        return new BodyFieldCondition(jsonPath, matcher);
//    }

    public static BodyFieldCondition bodyField(Matcher matcher){
        return new BodyFieldCondition(matcher);
    }

}
