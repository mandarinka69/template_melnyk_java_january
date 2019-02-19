import com.example.ProjectConfig;
import com.example.model.UserLoginFromMobile;
import com.example.services.UserApiService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.conditions.Conditions.statusCode;

public class DeleteNotification {
    private final UserApiService userApiService = new UserApiService();


    @BeforeAll
    static void setUp() {

        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiAuto();
    }

    @Test
    public void deleteNotificationTest(){
        //body
        UserLoginFromMobile userLoginFromMobile = new UserLoginFromMobile()
                .setPhoneNumberAuto("0971927133")
                .setPasswordAuto("25d55ad283aa400af464c76d713c07ad");
        Map<String, String> cookies =

                userApiService.sendPost(userLoginFromMobile, "https://login.ria.com/mobile/login")
                        .shouldHave(statusCode(200)).getCookies();


        //витягуємо всі підписки
        String notificationId =  userApiService.sendGet("http://api.mobile.rest.auto.ria.com/subscribe/subs/user/all/", cookies)
                .shouldHave(statusCode(200))
                .jsonPath()
                .and()
                .getString("id");

        System.out.println("-------------------");
        System.out.println(notificationId);


        //видаляємо підписку по ід
        userApiService.sendDelete("http://api.mobile.rest.auto.ria.com/subscribe/subs/"+notificationId, cookies);


    }
}
