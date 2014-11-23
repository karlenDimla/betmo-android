package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoBid {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public ArrayList<BetmoListItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BetmoListItem> items) {
        this.items = items;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemOffset() {
        return itemOffset;
    }

    public void setItemOffset(String itemOffset) {
        this.itemOffset = itemOffset;
    }

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("org")
    private String org;
    @SerializedName("notice")
    private String notice;
    @SerializedName("budget")
    private Double budget;
    @SerializedName("publishDate")
    private String publishDate;
    @SerializedName("closingDate")
    private String closingDate;
    private ArrayList<BetmoListItem> items;
    @SerializedName("itemCount")
    private String itemCount;
    @SerializedName("itemOffset")
    private String itemOffset;
    @SerializedName("itemTotal")
    private String itemTotal;
}
