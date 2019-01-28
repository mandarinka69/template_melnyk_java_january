import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.Card;
import utility.User;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class ApiCardTest {

    private Card card;
    private User user;

    @BeforeClass
    public void beforeApiCardTest() {
        user = new User();
        user.register();
        card = new Card(user.getId());
    }

    @Test
    public void testCanCreateCard() {
        card.register()
                .then()
                .assertThat()
                .statusCode(200).and()
                .body("id", not(isEmptyString()));
    }
}

