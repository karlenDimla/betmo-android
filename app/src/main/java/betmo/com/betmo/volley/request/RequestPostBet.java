package betmo.com.betmo.volley.request;

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
import betmo.com.betmo.volley.response.PostBetResponse;
import betmo.com.betmo.volley.response.PostCommentResponse;

/**
 * Created by kdimla on 11/22/14.
 */
public class RequestPostBet extends AbstractVolleyRequest<PostBetResponse> {
    private static final String TAG = RequestPostBet.class.getSimpleName();
    private String mComment;
    public RequestPostBet(int bidid, VolleyListener<PostBetResponse> listener) {
        super(Request.Method.POST, VolleyRequestType.POST_COMMENT, getPath(bidid), listener);
    }

    private static String getPath(int bidid) {
        return BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BIDDERS)
                + "/" + bidid+BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BET);
    }

    @Override
    protected Response<PostBetResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        PostBetResponse response = new PostBetResponse();
        try {
            String responseString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            Gson gson = new Gson();
            response = gson.fromJson(responseString, PostBetResponse.class);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        return Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

}

