package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoListItemComplete {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("qty")
    private int qty;
    @SerializedName("uom")
    private String uom;
    @SerializedName("budget")
    private Double budget;
    @SerializedName("status")
    private String status;
    @SerializedName("bidderCount")
    private int bidderCount;
    @SerializedName("bidderOffset")
    private int bidderOffset;
    @SerializedName("bidderTotal")
    private int bidderTotal;

    private ArrayList<BetmoBidderItem> bidders;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBidderCount() {
        return bidderCount;
    }

    public void setBidderCount(int bidderCount) {
        this.bidderCount = bidderCount;
    }

    public int getBidderOffset() {
        return bidderOffset;
    }

    public void setBidderOffset(int bidderOffset) {
        this.bidderOffset = bidderOffset;
    }

    public int getBidderTotal() {
        return bidderTotal;
    }

    public void setBidderTotal(int bidderTotal) {
        this.bidderTotal = bidderTotal;
    }

    public ArrayList<BetmoBidderItem> getBidders() {
        return bidders;
    }

    public void setBidders(ArrayList<BetmoBidderItem> bidders) {
        this.bidders = bidders;
    }
}
