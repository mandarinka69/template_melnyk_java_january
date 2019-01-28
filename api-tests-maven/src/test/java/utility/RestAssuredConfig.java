package utility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class RestAssuredConfig {
    public static final String baseURI = "http://35.246.254.107/";

    public RestAssuredConfig() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
//                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .build();
    }
}

