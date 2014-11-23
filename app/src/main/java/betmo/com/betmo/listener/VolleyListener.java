package betmo.com.betmo.listener;

/**
 * Created by kdimla on 11/22/14.
 */

import betmo.com.betmo.constant.VolleyRequestType;
import betmo.com.betmo.volley.error.CustomVolleyError;

public interface VolleyListener<T> {
    void onResponse(VolleyRequestType request, T response);

    void onErrorResponse(VolleyRequestType request, CustomVolleyError error);
}

