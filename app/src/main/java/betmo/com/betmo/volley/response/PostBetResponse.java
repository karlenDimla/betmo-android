package betmo.com.betmo.volley.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdimla on 11/22/14.
 */
public class PostBetResponse {
    @SerializedName("bet")
    private int bet;
    @SerializedName("updatedAt")
    private String updatedAt;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PostBetResponse(){

    }
}
