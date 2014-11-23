package betmo.com.betmo.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import betmo.com.betmo.constant.EndpointType;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.model.BetmoBid;
import betmo.com.betmo.model.BetmoListItem;

/**
 * Created by kdimla on 11/22/14.
 */
public class RequestGetBidInfo extends AbstractVolleyRequest<BetmoBid> {
    private BetmoBid mGetBidSpecificResponse;
    private static final String KEY_OFFSET="itemOffset";
    private static final String KEY_LIMIT="itemLimit";

    public RequestGetBidInfo(int bidid, int offset, int limit, VolleyListener<BetmoBid> listener){
        super(Method.GET, VolleyRequestType.GET_BID_INFO, getPath(bidid,offset,limit),listener);
    }

    private static String getPath(int bidid, int offset, int limit){
        return BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BID_INFO)
                +"/"+bidid
                +"?"+KEY_OFFSET+"="+offset
                +"&"+KEY_LIMIT+"="+limit;
    }

    @Override
    protected Response<BetmoBid> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String responseString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            Gson gson = new Gson();
            mGetBidSpecificResponse = gson.fromJson(responseString,BetmoBid.class);
            JSONObject mresponseJson = new JSONObject(responseString);
            JSONArray mItemsJsonArray = mresponseJson.getJSONArray("items");
            mGetBidSpecificResponse.setItems(new ArrayList<BetmoListItem>());
            for(int cnt = 0; cnt < mItemsJsonArray.length(); cnt ++){
                BetmoListItem item = gson.fromJson(mItemsJsonArray.get(cnt).toString(),BetmoListItem.class);
                mGetBidSpecificResponse.getItems().add(item);
            }
        } catch (UnsupportedEncodingException use) {
            use.printStackTrace();
        } catch(JSONException je){
            je.printStackTrace();
        }

        return Response.success(mGetBidSpecificResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
