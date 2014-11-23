package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdimla on 11/22/14.
 */
public class Comment {
    @SerializedName("firstName")
    private String firstname;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("comment")
    private String comment;
    @SerializedName("createdAt")
    private String createdAt;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
