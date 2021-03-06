package betmo.com.betmo.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import betmo.com.betmo.font.Fonts;

/**
 * Created by kdimla on 11/22/14.
 */
public class NordicaTextView extends TextView {

    public NordicaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * When Android sets the typeface, intercept it and use the fonts declared
     * in the application instead. This works with type styles (bold, italic)
     * declared via XML as well.
     */
    @Override
    public void setTypeface(Typeface typeface, int style) {
        switch (style) {
            case Typeface.BOLD:
                super.setTypeface(Fonts.NORDICA_B);
                break;
            case Typeface.ITALIC:
                super.setTypeface(Fonts.NORDICA_H);
                break;
            default:
                super.setTypeface(Fonts.NORDICA_T);
                break;
        }
    }
}