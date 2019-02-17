import com.example.ProjectConfig;
import com.example.model.CreateNotification;
import com.example.model.UserLoginFromMobile;
import com.example.services.UserApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.example.conditions.Conditions.statusCode;

public class AllNotification {
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


        //all notification
        userApiService.sendGet(" http://api.mobile.rest.auto.ria.com/subscribe/profiles/user/all/?withSubs=1", cookies)
                .shouldHave(statusCode(200));

    }

}
