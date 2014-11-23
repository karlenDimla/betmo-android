package betmo.com.betmo.utility;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by kdimla on 11/22/14.
 */
public class DisplayUtility {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
