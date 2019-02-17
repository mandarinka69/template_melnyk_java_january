import com.github.javafaker.Faker;

import java.util.Locale;

public class BaseTest {

    protected final Faker faker = new Faker(new Locale(("ru")));
}
