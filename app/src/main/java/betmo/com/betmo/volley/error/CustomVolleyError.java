package betmo.com.betmo.volley.error;

import com.android.volley.VolleyError;

import betmo.com.betmo.constant.VolleyErrorCode;

/**
 * Created by kdimla on 11/22/14.
 */
public class CustomVolleyError {
    private VolleyErrorCode mVolleyErrorCode;
    private String mMessage;
    private VolleyError mError;

    public void setErrorCode(VolleyErrorCode volleyErrorCode) {
        this.mVolleyErrorCode = volleyErrorCode;
    }

    public VolleyErrorCode getErrorCode() {
        return mVolleyErrorCode;
    }

    public void setMessage(String msg) {
        this.mMessage = msg;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setError(VolleyError error) {
        this.mError = error;
    }

    public VolleyError getError() {
        return mError;
    }
}
