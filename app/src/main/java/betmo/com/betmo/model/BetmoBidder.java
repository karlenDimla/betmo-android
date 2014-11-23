package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kdimla on 11/23/14.
 */
public class BetmoBidder {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("org")
    private String org;
    @SerializedName("awarded")
    private Boolean awarded;
    @SerializedName("popular")
    private Boolean popular;
    @SerializedName("bets")
    private int bets;
    @SerializedName("budget")
    private Double budget;
    @SerializedName("commentCount")
    private int commentCount;
    @SerializedName("commentOffset")
    private int commentOffset;
    @SerializedName("commentSort")
    private int commentSort;
    @SerializedName("commentTotal")
    private int commentTotal;

    private ArrayList<Comment> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Boolean getAwarded() {
        return awarded;
    }

    public void setAwarded(Boolean awarded) {
        this.awarded = awarded;
    }

    public Boolean getPopular() {
        return popular;
    }

    public void setPopular(Boolean popular) {
        this.popular = popular;
    }

    public int getBets() {
        return bets;
    }

    public void setBets(int bets) {
        this.bets = bets;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentOffset() {
        return commentOffset;
    }

    public void setCommentOffset(int commentOffset) {
        this.commentOffset = commentOffset;
    }

    public int getCommentSort() {
        return commentSort;
    }

    public void setCommentSort(int commentSort) {
        this.commentSort = commentSort;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
