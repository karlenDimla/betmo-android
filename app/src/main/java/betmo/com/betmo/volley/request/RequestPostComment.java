package betmo.com.betmo.volley.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import betmo.com.betmo.constant.EndpointType;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.model.PostCommentBody;
import betmo.com.betmo.volley.response.PostCommentResponse;

/**
 * Created by kdimla on 11/22/14.
 */
public class RequestPostComment extends AbstractVolleyRequest<PostCommentResponse> {
    private static final String TAG = RequestPostComment.class.getSimpleName();
    private String mComment;
    public RequestPostComment(int bidid, String comment, VolleyListener<PostCommentResponse> listener) {
        super(Request.Method.POST, VolleyRequestType.POST_COMMENT, getPath(bidid), listener);
        this.mComment = comment;
    }

    private static String getPath(int bidid) {
        return BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BIDDERS)
                + "/" + bidid+BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_COMMENT);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Gson gson = new Gson();
        PostCommentBody postCommentBody
                = new PostCommentBody(mComment);
        String body = gson.toJson(postCommentBody);
        Log.d(TAG, body);
        return body.getBytes();
    }

    @Override
    protected Response<PostCommentResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        PostCommentResponse response = new PostCommentResponse();
        try {
            String responseString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            Gson gson = new Gson();
            response = gson.fromJson(responseString, PostCommentResponse.class);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        return Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

}
