package betmo.com.betmo.controller;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import betmo.com.betmo.font.Fonts;

/**
 * Created by kdimla on 11/22/14.
 */
public class BetmoApplication extends Application {
    private static final String TAG = BetmoApplication.class.getSimpleName();
    private static Context sContext;
    private static BetmoEngine sEngine;

    public static BetmoEngine getEngine() {
        if (sEngine == null) {
            sEngine = BetmoEngine.getInstance(sContext);
        }

        return sEngine;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
        initializeFonts();
    }

    private void initialize() {
        sContext = getApplicationContext();
    }

    private void initializeFonts() {
        Fonts.AERO_B = Typeface.createFromAsset(getAssets(), Fonts.PATH_AERO_B);
        Fonts.AERO_L = Typeface.createFromAsset(getAssets(), Fonts.PATH_AERO_L);
        Fonts.AERO_R = Typeface.createFromAsset(getAssets(), Fonts.PATH_AERO_R);
        Fonts.NORDICA_B = Typeface.createFromAsset(getAssets(), Fonts.PATH_NORDICA_B);
        Fonts.NORDICA_H = Typeface.createFromAsset(getAssets(), Fonts.PATH_NORDICA_H);
        Fonts.NORDICA_R = Typeface.createFromAsset(getAssets(), Fonts.PATH_NORDICA_R);
        Fonts.NORDICA_T = Typeface.createFromAsset(getAssets(), Fonts.PATH_NORDICA_T);
        Fonts.COMFORTAA_B = Typeface.createFromAsset(getAssets(), Fonts.PATH_COMFORTAA_B);
        Fonts.COMFORTAA_L = Typeface.createFromAsset(getAssets(), Fonts.PATH_COMFORTAA_L);
        Fonts.COMFORTAA_R = Typeface.createFromAsset(getAssets(), Fonts.PATH_COMFORTAA_R);
    }

}