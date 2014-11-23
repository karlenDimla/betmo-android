package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoBidderItem {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("budget")
    private float budget;
    @SerializedName("awarded")
    private boolean awarded;
    @SerializedName("popular")
    private boolean popular;
    @SerializedName("bets")
    private int bets;

    public int getBets() {
        return bets;
    }

    public void setBets(int bets) {
        this.bets = bets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public boolean isAwarded() {
        return awarded;
    }

    public void setAwarded(boolean awarded) {
        this.awarded = awarded;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

}
