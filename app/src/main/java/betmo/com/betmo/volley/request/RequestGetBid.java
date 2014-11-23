package betmo.com.betmo.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import betmo.com.betmo.constant.EndpointType;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.volley.response.GetBidResponse;

/**
 * Created by kdimla on 11/22/14.
 */
public class RequestGetBid extends AbstractVolleyRequest<GetBidResponse> {
    private GetBidResponse mGetBidResponse;
    private static final String KEY_OFFSET="offset";
    private static final String KEY_FILTER="filter";
    private static final String KEY_LIMIT="limit";
    public RequestGetBid(String filter, int offset, int limit, VolleyListener<GetBidResponse> listener){
        super(Method.GET, VolleyRequestType.GET_BIDS, getPath(filter,offset,limit),listener);
    }

    private static String getPath(String filter, int offset, int limit){
        return BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BIDS)
                +"?"+KEY_OFFSET+"="+offset
                +"&"+KEY_LIMIT+"="+limit
                +"&"+KEY_FILTER+"="+filter;
    }

    @Override
    protected Response<GetBidResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String responseString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            mGetBidResponse = new GetBidResponse(new JSONObject(responseString));
        } catch (UnsupportedEncodingException use) {
            use.printStackTrace();
        } catch(JSONException je){
            je.printStackTrace();
        }

        return Response.success(mGetBidResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }


}
