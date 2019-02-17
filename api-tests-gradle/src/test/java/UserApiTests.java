import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.responces.UserListResponce;
import com.example.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.example.conditions.Conditions.bodyField;
import static com.example.conditions.Conditions.statusCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiTests extends BaseTest{

    private final UserApiService userApiService = new UserApiService();

    @BeforeAll
    static void setUp() {
        Map myVars = new HashMap();
        myVars.put("env", System.getProperty("env", "test")); // here!

        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class, myVars).apiPath();
    }

    @Test
    void testCanRegisterUser() {

        UserPayload userPayload = new UserPayload()

//                .setUsername(RandomStringUtils.randomAlphanumeric(6))
                .setUsername(faker.name().username())
                .setPassword("12345")
                .setEmail("test@gmail.com");

        String id = userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyString())))
                .getValue("id")
//                .shouldHave(bodyField(containsString("id")))
        ;

        UserListResponce users = userApiService.getAllUsers().asPojo(UserListResponce.class);

//        assertThat(users.getEmbedded().getCustomer()).hasSize(5);
        assertThat(users.getEmbedded().getCustomer()).size().isGreaterThan(20);

//        CustomerAssert.assertThat(users.getEmbedded().getCustomer().size())

        System.out.println(users);

    }



}
