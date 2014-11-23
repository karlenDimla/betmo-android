package betmo.com.betmo.controller;

import android.content.Context;

import com.android.volley.toolbox.ImageLoader;

import betmo.com.betmo.helper.VolleyHelper;
import betmo.com.betmo.manager.AccountManager;
import betmo.com.betmo.manager.ApiEndpointManager;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoEngine {
    private static final String TAG = BetmoEngine.class.getSimpleName();
    private static BetmoEngine mInstance;
    private Context mContext;

    private ApiEndpointManager mApiEndpointManager;
    private AccountManager mAccountManager;
    private VolleyHelper mVolleyHelper;

    private BetmoEngine(Context context) {
        this.mContext = context;
        this.mApiEndpointManager = new ApiEndpointManager(mContext);
        this.mVolleyHelper = VolleyHelper.getInstance(mContext);
        this.mAccountManager = AccountManager.getInstance(mContext);
    }

    public static BetmoEngine getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BetmoEngine(context);
        }
        return mInstance;
    }

    public ApiEndpointManager getApiEndpointManager() {
        return mApiEndpointManager;
    }

    public AccountManager getAccountManager() {
        return mAccountManager;
    }

    public VolleyHelper getVolleyHelper() {
        return mVolleyHelper;
    }

    public ImageLoader getImageLoader() {
        return mVolleyHelper.getImageLoader();
    }
}

