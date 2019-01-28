package utility;


import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.CardModel;

import java.util.Locale;

public class Card {

    private CardModel cardModel;
    private Faker faker = new Faker(Locale.US);

    public String getExpires(){
        return cardModel.getExpires();
    }

    public String getLongNum(){
        return cardModel.getLongNum();
    }

    public String getCcv(){
        return cardModel.getCcv();
    }

    public String getUserID(){
        return cardModel.getUserID();
    }

    public String getCardID(){
        return cardModel.getCardID();
    }

    public Card(String userId) {
        this.cardModel = new CardModel().setUserID(userId).setLongNum(faker.business().creditCardNumber())
                .setExpires(faker.business().creditCardExpiry()).setCcv(faker.numerify("3"));
    }

    public Response register() {
        Response registerResponse = RestAssured.given()
                .body(this.cardModel)
                .when()
                .post("cards");
        this.cardModel.setCardID(registerResponse.then().extract().body().jsonPath().getString("id"));
        return registerResponse;

    }
}
