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
import betmo.com.betmo.model.BetmoBidderItem;
import betmo.com.betmo.model.BetmoListItemComplete;

/**
 * Created by kdimla on 11/22/14.
 */
public class RequestGetItemInfo extends AbstractVolleyRequest<BetmoListItemComplete> {
    private BetmoListItemComplete mCompleteListItem;
    private static final String KEY_OFFSET = "bidderOffset";
    private static final String KEY_LIMIT = "bidderLimit";

    public RequestGetItemInfo(int itemid, int bidid, int offset, int limit, VolleyListener<BetmoListItemComplete> listener) {
        super(Method.GET, VolleyRequestType.GET_ITEM_INFO, getPath(itemid, bidid,offset, limit), listener);
    }

    private static String getPath(int itemid, int bidid, int offset, int limit) {
        return BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_BIDS)
                +"/"+bidid+BetmoApplication.getEngine().getApiEndpointManager().getUrl(EndpointType.TYPE_ITEM_INFO)
                + "/" + itemid
                + "?" + KEY_OFFSET + "=" + offset
                + "&" + KEY_LIMIT + "=" + limit;
    }

    @Override
    protected Response<BetmoListItemComplete> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String responseString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            Gson gson = new Gson();
            mCompleteListItem = gson.fromJson(responseString, BetmoListItemComplete.class);
            JSONObject mresponseJson = new JSONObject(responseString);
            JSONArray mItemsJsonArray = mresponseJson.getJSONArray("bidders");
            mCompleteListItem.setBidders(new ArrayList<BetmoBidderItem>());
            for (int cnt = 0; cnt < mItemsJsonArray.length(); cnt++) {
                BetmoBidderItem item = gson.fromJson(mItemsJsonArray.get(cnt).toString(), BetmoBidderItem.class);
                mCompleteListItem.getBidders().add(item);
            }
        } catch (UnsupportedEncodingException use) {
            use.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }

        return Response.success(mCompleteListItem, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}