package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoBidItem {
    public String getBidTitle() {
        return bidTitle;
    }

    public void setBidTitle(String bidTitle) {
        this.bidTitle = bidTitle;
    }

    public String getBidCategory() {
        return bidCategory;
    }

    public void setBidCategory(String bidCategory) {
        this.bidCategory = bidCategory;
    }

    public String getBidOrg() {
        return bidOrg;
    }

    public void setBidOrg(String bidOrg) {
        this.bidOrg = bidOrg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BetmoBidItem(int id, String title, String category, String org) {
        this.id = id;
        this.bidTitle = title;
        this.bidCategory = category;
        this.bidOrg = org;
    }

    public BetmoBidItem(){

    }

    @SerializedName("org")
    private String bidOrg;
    @SerializedName("id")
    private int id;
    @SerializedName("category")
    private String bidCategory;
    @SerializedName("title")
    private String bidTitle;

}
