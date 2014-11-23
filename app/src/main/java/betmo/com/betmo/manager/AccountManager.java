package betmo.com.betmo.manager;

import android.content.Context;
import android.text.TextUtils;

import betmo.com.betmo.constant.SharedPreferenceConstants;

/**
 * Created by kdimla on 11/22/14.
 */
public class AccountManager {
    private static AccountManager mInstance;
    private Context mContext;
    private SharedPreferenceManager mSharedPreferenceManager;

    public static AccountManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new AccountManager(context);
        }
        return mInstance;
    }

    private AccountManager(Context context){
        mContext = context;
        mSharedPreferenceManager = SharedPreferenceManager.getInstance(context);
    }

    public String getToken(){
        String token = mSharedPreferenceManager.getString(SharedPreferenceConstants.SP_TOKEN,"");
        return token;
    }

    public boolean isLoggedIn(){
        String token = mSharedPreferenceManager.getString(SharedPreferenceConstants.SP_TOKEN,"");
        if(TextUtils.isEmpty(token)){
            return false;
        }
        return true;
    }
}
