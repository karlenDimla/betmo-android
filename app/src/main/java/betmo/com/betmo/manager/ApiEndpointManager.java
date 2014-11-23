package betmo.com.betmo.manager;

import android.content.Context;

import betmo.com.betmo.R;
import betmo.com.betmo.constant.EndpointType;
import betmo.com.betmo.constant.Endpoints;

/**
 * Created by kdimla on 11/22/14.
 */
public class ApiEndpointManager {
    private String mBaseUrl;
    public ApiEndpointManager(Context context) {
        mBaseUrl = context.getResources().getString(R.string.betmo_base_url);
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public String getUrl(int endpointType) {
        String url = mBaseUrl;

        switch (endpointType) {
            case EndpointType.TYPE_BIDS:
            case EndpointType.TYPE_BID_INFO:
                url = url+Endpoints.ENPOINT_BIDS;
                break;
            case EndpointType.TYPE_ITEM_INFO:
                url = Endpoints.ENDPOINT_ITEM;
                break;
            case EndpointType.TYPE_BIDDERS:
                url = url+Endpoints.ENDPOINT_BIDDERS;
                break;
            case EndpointType.TYPE_COMMENT:
                url = Endpoints.ENDPOINT_COMMENT;
                break;
            case EndpointType.TYPE_BET:
                url = Endpoints.ENDPOINT_BETS;
                break;
        }
        return url;
    }
}
