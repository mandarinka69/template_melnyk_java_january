package utility;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import java.util.List;
import java.util.Locale;

public class User extends RestAssuredConfig {

    private UserModel userModel;
    private Faker faker = new Faker(Locale.US);

    public User() {
        this.userModel = new UserModel().setUsername(faker.name().username())
                .setPassword(faker.code().asin())
                .setEmail(faker.internet().emailAddress());
    }

    public String getName() {
        return userModel.getUsername();
    }

    public String getPassword() {
        return userModel.getPassword();
    }

    public String getEmail() {
        return userModel.getEmail();
    }

    public String getId() {
        return userModel.getId();
    }

    public User(String username, String password, String email) {
        this.userModel = new UserModel().setUsername(username).setPassword(username).setEmail(username);
    }

    public Response register() {
        Response registerResponse = RestAssured.given()
                .body(this.userModel)
                .when()
                .post("register");
        this.userModel.setId(registerResponse.then().extract().body().jsonPath().getString("id"));
        return registerResponse;
    }

    public void delete() {
        RestAssured.given()
                .delete(baseURI + "customers/" + userModel.getId());
    }

    public static List<String> getAllCustomersIdsList() {
        return RestAssured.when()
                .get(baseURI + "customers/")
                .then()
                .contentType(ContentType.TEXT).extract().body().jsonPath().getList("_embedded.customer.id");

    }
}

