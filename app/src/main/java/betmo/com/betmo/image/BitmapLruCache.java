package betmo.com.betmo.image;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * A straightforward implementation of the {@link com.android.volley.toolbox.ImageLoader.ImageCache
 * ImageCache} in LruCache provided by Android + Support Library. Originally
 * written by Ficus Kirkpatrick: https://gist.github.com/ficusk/5614325
 *
 * @author jerieljan
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

    /**
     * Creates a new BitmapLruCache with the default cache size.
     */
    public BitmapLruCache() {
        this(getDefaultLruCacheSize());
    }

    /**
     * Creates a new BitmapLruCache with a defined amount of KB on memory.
     */
    public BitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    /**
     * Retrieves the default LruCache size. The value is based on 1/8 of the
     * devices' maximum memory.
     */
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory / 8;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

}
