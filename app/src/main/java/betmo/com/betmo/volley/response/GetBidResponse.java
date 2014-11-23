package betmo.com.betmo.volley.response;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import betmo.com.betmo.model.BetmoBidItem;

/**
 * Created by kdimla on 11/22/14.
 */
public class GetBidResponse {

    private String filter;
    private int offset;
    private int count;
    private int total;
    private ArrayList<BetmoBidItem> bids;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<BetmoBidItem> getBids() {
        return bids;
    }

    public void setBids(ArrayList<BetmoBidItem> bids) {
        this.bids = bids;
    }

    public GetBidResponse(JSONObject message){
        this.filter = message.optString("filter");
        this.count = message.optInt("count");
        this.total = message.optInt("total");
        this.bids = new ArrayList<BetmoBidItem>();
        JSONArray bidsArray = message.optJSONArray("bids");
        Gson gson = new Gson();
        for(int cnt =0; cnt < bidsArray.length();cnt++){
            BetmoBidItem item;
            try {
                 item =  gson.fromJson(bidsArray.get(cnt).toString(),BetmoBidItem.class);
            }catch(JSONException je){
                item = null;
            }
            bids.add(item);
        }
    }
}
