package betmo.com.betmo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kdimla on 11/22/14.
 */
public class CircleTextView extends TextView {
    private Path mClipPath;
    private RectF mRect;

    public CircleTextView(Context context) {
        super(context);
        initialize();
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CircleTextView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        mClipPath = new Path();
        mRect = new RectF(0, 0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRect.set(0, 0, this.getWidth(), this.getHeight());
        mClipPath.reset();
        mClipPath.addOval(mRect, Path.Direction.CW);
        setHardwareAccelerated(this, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (!canvas.isHardwareAccelerated()) {
                canvas.clipPath(mClipPath);
            }
        } else {
            canvas.clipPath(mClipPath);
        }
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        super.onDraw(canvas);
    }

    private static void setHardwareAccelerated(View view, boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (enabled)
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            else view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }
}
