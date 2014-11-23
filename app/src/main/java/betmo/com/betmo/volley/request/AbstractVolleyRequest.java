package betmo.com.betmo.volley.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import betmo.com.betmo.constant.VolleyErrorCode;
import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.controller.BetmoApplication;
import betmo.com.betmo.listener.VolleyListener;
import betmo.com.betmo.manager.AccountManager;
import betmo.com.betmo.volley.error.CustomVolleyError;

/**
 * Created by kdimla on 11/22/14.
 */
public abstract class AbstractVolleyRequest<T> extends Request<T> {
    private static final String TAG = AbstractVolleyRequest.class.getSimpleName();
    private final String HEADER_TOKEN_KEY="token";

    protected VolleyListener<T> mListener;
    protected VolleyRequestType mRequest;
    protected VolleyErrorCode mVolleyErrorCode;
    protected String mErrorMessage;

    private boolean mHasCustomErrorMessage;
    private final String BODY_CONTENT_TYPE = "application/json;charset=utf-8;";

    public AbstractVolleyRequest(int method, VolleyRequestType request, String url, VolleyListener<T> listener) {
        super(method, url, null);
        this.mRequest = request;
        this.mListener = listener;
        this.mVolleyErrorCode = VolleyErrorCode.NO_ERROR;
        this.mErrorMessage = "";
        this.mHasCustomErrorMessage = false;
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    protected void setCustomErrorMessage(String errorMessage) {
        mHasCustomErrorMessage = true;
        mErrorMessage = errorMessage;
    }

    @Override
    protected void deliverResponse(T response) {
        if (mListener != null) {
            mListener.onResponse(mRequest, response);
        }
    }

    @Override
    public void deliverError(VolleyError volleyerror) {
        Log.d(TAG, volleyerror.toString());
        if(volleyerror.networkResponse != null && volleyerror.networkResponse.data != null){
            if (!mHasCustomErrorMessage) {
                mErrorMessage = new String(volleyerror.networkResponse.data);
//                mErrorMessage = trimMessage(mErrorMessage, JSON_MESSAGE_KEY);
            }
        }
        CustomVolleyError error = new CustomVolleyError();
        error.setErrorCode(mVolleyErrorCode);
        error.setMessage(mErrorMessage);
        error.setError(volleyerror);

        if (mListener != null) {
            mListener.onErrorResponse(mRequest, error);
        }
    }

    @Override
    public Priority getPriority() {
        return Priority.NORMAL;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put(HEADER_TOKEN_KEY, BetmoApplication.getEngine().getAccountManager().getToken());
        return header;
    }

    @Override
    public String getBodyContentType() {
        return BODY_CONTENT_TYPE;
    }

}

