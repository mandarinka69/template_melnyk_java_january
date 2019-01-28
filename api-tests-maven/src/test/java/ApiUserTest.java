import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.User;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class ApiUserTest {

    private User userForRegistrationCheck, userForDeletionCheck;

    @BeforeClass
    public void beforeSockShopApiUserTest() {
        userForRegistrationCheck = new User();
        userForDeletionCheck = new User();
    }

    @Test
    void testCanRegisterUser() {
        userForRegistrationCheck.register()
                .then()
                .assertThat()
                .statusCode(200).and()
                .body("id", not(isEmptyString()));
    }

    @Test
    public void testCanDeleteUser() {
        userForDeletionCheck.register();
        userForDeletionCheck.delete();

        Assert.assertFalse(User.getAllCustomersIdsList().contains(userForDeletionCheck.getId()));
    }

    @AfterClass
    public void afterSockShopApiTest() {
        userForRegistrationCheck.delete();
    }
}

