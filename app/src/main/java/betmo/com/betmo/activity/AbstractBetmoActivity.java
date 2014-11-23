package betmo.com.betmo.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;

import betmo.com.betmo.R;
import betmo.com.betmo.font.Fonts;
import betmo.com.betmo.font.TypefaceSpan;

/**
 * Created by kdimla on 11/22/14.
 */
public abstract class AbstractBetmoActivity extends ActionBarActivity {
    private ActionBar mActionBar;

    protected abstract String setInitialTitleOfActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActionBar();
    }


    private void initializeActionBar() {
        mActionBar = getSupportActionBar();
        changeActionBarTitle(setInitialTitleOfActionBar());
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setBackgroundDrawable(getResources().getDrawable(R.color.betmo_color_actionbar));
    }

    public void changeActionBarTitle(String title) {
        TypefaceSpan typefaceSpan = new TypefaceSpan(this, Fonts.PATH_AERO_R);
        SpannableString spannableString = new SpannableString(title+"  ");
        spannableString.setSpan(typefaceSpan, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mActionBar.setTitle(spannableString);
    }

    public String getActionBarTitle() {
        return getSupportActionBar().getTitle().toString();
    }
}
