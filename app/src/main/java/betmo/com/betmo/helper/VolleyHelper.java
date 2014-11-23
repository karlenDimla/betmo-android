package betmo.com.betmo.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import betmo.com.betmo.image.BitmapLruCache;

/**
 * Created by kdimla on 11/22/14.
 */
public class VolleyHelper {

    private static VolleyHelper sInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private BitmapLruCache mBitmapLruCache;

    private VolleyHelper(Context context) {
        setRequestQueue(Volley.newRequestQueue(context));

        // Initialize Bitmap Cache
        mBitmapLruCache = new BitmapLruCache();
        setImageLoader(new ImageLoader(getRequestQueue(), mBitmapLruCache));

        // Start processing Request Queue
        getRequestQueue().start();
    }

    public static VolleyHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new VolleyHelper(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void setRequestQueue(RequestQueue mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void setImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    public void clearImageLoader() {
        mBitmapLruCache.evictAll();
    }

    public Request<?> addToRequestQueue(final Request<?> request) {
        getRequestQueue().add(request);
        return request;
    }
}
