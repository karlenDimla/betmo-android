package betmo.com.betmo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import betmo.com.betmo.R;
import betmo.com.betmo.utility.DisplayUtility;

/**
 * Created by kdimla on 11/22/14.
 */
public class RoundedCornersLinearLayout extends LinearLayout {
        private static final int DEFAULT_RADIUS_DP = 15;
        private Context mContext;
        private Path mClipPath;
        private RectF mRect;
        private int mRadius;

        public RoundedCornersLinearLayout(Context context) {
            super(context);
            mContext = context;
            initialize();
        }

        public RoundedCornersLinearLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            mContext = context;
            initialize();
            customizeUsingAttributes(attrs);
        }

        public RoundedCornersLinearLayout(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            mContext = context;
            initialize();
            customizeUsingAttributes(attrs);
        }

    private void initialize() {
        mRadius = DisplayUtility.dpToPx(getContext(), DEFAULT_RADIUS_DP);
        mClipPath = new Path();
        mRect = new RectF(0, 0, 0, 0);
    }

    private void customizeUsingAttributes(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.RoundedLinearLayout);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.RoundedLinearLayout_roundedLinearLayout_radius:
                    mRadius = (int) a.getDimension(attr, 0);
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRect.set(0, 0, this.getWidth(), this.getHeight());
        mClipPath.reset();
        mClipPath.addRoundRect(mRect, mRadius, mRadius, Path.Direction.CW);
        setHardwareAccelerated(this, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (!canvas.isHardwareAccelerated()) {
                canvas.clipPath(mClipPath);
            }
        }
        else {
            canvas.clipPath(mClipPath);
        }
        super.onDraw(canvas);
    }

    private static void setHardwareAccelerated(View view, boolean enabled){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            if(enabled)
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            else view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

    }
}
