package model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CardModel {

    @SerializedName("expires")
    private String expires;

    @SerializedName("longNum")
    private String longNum;

    @SerializedName("ccv")
    private String ccv;

    @SerializedName("userID")
    private String userID;

    private String cardID;

    public CardModel setCardID(String cardID){
        this.cardID = cardID;
        return this;
    }

    public CardModel setExpires(String expires){
        this.expires = expires;
        return this;
    }

    public String getExpires(){
        return expires;
    }

    public String getCardID(){
        return cardID;
    }

    public CardModel setLongNum(String longNum){
        this.longNum = longNum;
        return this;
    }

    public String getLongNum(){
        return longNum;
    }

    public CardModel setCcv(String ccv){
        this.ccv = ccv;
        return this;
    }

    public String getCcv(){
        return ccv;
    }

    public CardModel setUserID(String userID){
        this.userID = userID;
        return this;
    }

    public String getUserID(){
        return userID;
    }

    @Override
    public String toString(){
        return
                "Card{" +
                        "expires = '" + expires + '\'' +
                        ",longNum = '" + longNum + '\'' +
                        ",ccv = '" + ccv + '\'' +
                        ",userID = '" + userID + '\'' +
                        "}";
    }
}

