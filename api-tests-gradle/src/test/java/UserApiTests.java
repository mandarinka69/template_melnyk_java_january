import com.example.conditions.Conditions;
import com.example.conditions.StatusCodeCondition;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class UserApiTests {

    private final UserApiService userApiService = new UserApiService();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://35.246.254.107/";
    }

    @Test
    void testCanRegisterUser() {

        UserPayload userPayload = new UserPayload()

                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setPassword("12345")
                .setEmail("test@gmail.com");

        userApiService.registerUser(userPayload)
                .shouldHave(Conditions.statusCode(200));

//                .body("id", not(isEmptyString()));

    }

    @Test
    void testCanNotRegisterUser() {

        UserPayload userPayload = new UserPayload()

                .setPassword("12345")
                .setEmail("test@gmail.com");

        userApiService.registerUser(userPayload)
                .shouldHave(Conditions.statusCode(200));
    }



//    @Test
//    void testDeleteCustomers(){
//        UserPayload userPayload = new UserPayload()
//
//                .setUsername(RandomStringUtils.randomAlphanumeric(6))
//                .setPassword("cdcdf")
//                .setEmail("test@gmail.com");
//
//
//       String user_id = RestAssured
//                .given().contentType(ContentType.JSON).log().all()
//                .body(userPayload)
//                .when()
//                .post("register")
//               .body().jsonPath().getString("id");
//
//        System.out.println("--------------");
//        System.out.println(user_id);
//
//        RestAssured
//                .given().contentType(ContentType.JSON).log().all()
//                .delete("http://35.246.254.107/customers/"+user_id+"")
//                .then().log().all()
//                .statusCode(200);
//
//    }
}
