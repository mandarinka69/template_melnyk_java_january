import com.model.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class UserApiTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://35.246.254.107/";
    }

    @Test
    void testCanRegisterUser() {

        UserPayload userPayload = new UserPayload()

                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setPassword("cdcdf")
                .setEmail("test@gmail.com");


        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(userPayload)
                .when()
                .post("register")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("id", not(isEmptyString()));

    }

    @Test
    void testDeleteCustomers(){
        UserPayload userPayload = new UserPayload()

                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setPassword("cdcdf")
                .setEmail("test@gmail.com");


       String user_id = RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .body(userPayload)
                .when()
                .post("register")
               .body().jsonPath().getString("id");

        System.out.println("--------------");
        System.out.println(user_id);

        RestAssured
                .given().contentType(ContentType.JSON).log().all()
                .delete("http://35.246.254.107/customers/"+user_id+"")
                .then().log().all()
                .statusCode(200);




    }
}
