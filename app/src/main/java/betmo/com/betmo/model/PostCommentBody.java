package betmo.com.betmo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kdimla on 11/22/14.
 */
public class PostCommentBody {
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @SerializedName("comment")
    private String comment;
    public PostCommentBody(String comment){
        this.comment = comment;
    }
}
