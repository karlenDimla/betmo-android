package betmo.com.betmo.volley.response;

import com.google.gson.annotations.SerializedName;

import betmo.com.betmo.model.PostCommentBody;

/**
 * Created by kdimla on 11/22/14.
 */
public class PostCommentResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("createdAt")
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public PostCommentResponse(){

    }
}
