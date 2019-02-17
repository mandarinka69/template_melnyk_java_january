import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.conditions.Conditions;
import com.example.model.CreateNotification;
import com.example.model.UserLoginFromMobile;
import com.example.services.UserApiService;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.example.conditions.Conditions.bodyField;
import static com.example.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.*;

public class CreateNewNotificationTest {
    private final UserApiService userApiService = new UserApiService();


    @BeforeAll
    static void setUp() {

        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiAuto();
    }

    @Test
    public void createNotificationTest(){
        //body

        UserLoginFromMobile userLoginFromMobile = new UserLoginFromMobile()
                .setPhoneNumberAuto("0971927133")
                .setPasswordAuto("25d55ad283aa400af464c76d713c07ad");
        Map<String, String> cookies =

        userApiService.sendPost(userLoginFromMobile, "https://login.ria.com/mobile/login")
                .shouldHave(statusCode(200)).getCookies();


        //create notification
        CreateNotification createNotification = new CreateNotification().setQueryBodyNotification("year[0].lte=1991&categories.main.id=6");

        userApiService.sendPost(createNotification, " http://api.mobile.rest.auto.ria.com/subscribe/subs", cookies)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("active", containsString("1")))
                .shouldHave(bodyField("activeMobile", not(isEmptyString())))
                .shouldHave(bodyField("id", not(isEmptyString())))
                .shouldHave(bodyField("profileId", not(isEmptyString())))
                .shouldHave(bodyField("stringId", not(isEmptyString())))
                .shouldHave(bodyField("stringIdNew", not(isEmptyString())))
                .shouldHave(bodyField("createDate", not(isEmptyString())))
                .shouldHave(bodyField("subscribeString.id", not(isEmptyString())))
                .shouldHave(bodyField("subscribeString.string", containsString("year[0].lte=1991&categories.main.id=6")))
                .shouldHave(bodyField("subscribeString.md5", not(isEmptyString())))
                .shouldHave(bodyField("subscribeSchedule.subscribeId", not(isEmptyString())))
                .shouldHave(bodyField("subscribeSchedule.cron", containsString("0 */10 * * * *")))
                .shouldHave(bodyField("subscribeSchedule.zone", containsString("+0200")));

    }


}
