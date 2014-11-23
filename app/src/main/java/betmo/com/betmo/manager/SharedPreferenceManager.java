package betmo.com.betmo.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kdimla on 11/22/14.
 */
public class SharedPreferenceManager {
        public static final String FILENAME = ".surprisePref";

        private static SharedPreferenceManager sInstance;
        private static SharedPreferences sSharedPreferences;
        private static SharedPreferences.Editor sPreferencesEditor;

        private SharedPreferenceManager(Context context) {
            sSharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
            sPreferencesEditor = sSharedPreferences.edit();
        }

    public static SharedPreferenceManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferenceManager(context);
        }

        return sInstance;
    }

    public String getString(String key, String defaultValue) {
        return sSharedPreferences.getString(key, defaultValue);
    }

    public SharedPreferenceManager putString(String key, String value) {
        sPreferencesEditor.putString(key, value);
        sPreferencesEditor.commit();
        return sInstance;
    }

    public int getInt(String key, int defaultValue) {
        return sSharedPreferences.getInt(key, defaultValue);
    }

    public SharedPreferenceManager putInt(String key, int value) {
        sPreferencesEditor.putInt(key, value);
        sPreferencesEditor.commit();
        return sInstance;
    }

    public Long getLong(String key, Long defaultValue) {
        return sSharedPreferences.getLong(key, defaultValue);
    }

    public SharedPreferenceManager putLong(String key, Long value) {
        sPreferencesEditor.putLong(key, value);
        sPreferencesEditor.commit();
        return sInstance;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sSharedPreferences.getBoolean(key, defaultValue);
    }

    public SharedPreferenceManager putBoolean(String key, Boolean value) {
        sPreferencesEditor.putBoolean(key, value);
        sPreferencesEditor.commit();
        return sInstance;
    }

}
