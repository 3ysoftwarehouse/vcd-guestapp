package br.com.a3ysoftwarehouse.vcdguest.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Iago Belo on 05/07/17.
 */

public class PreferencesHelper implements IPreferencesHelper {
    // Constants
    private static final String IS_LOGGED_KEY = "is_logged";

    private final SharedPreferences mPrefs;

    public PreferencesHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getIsLogged() {
        return mPrefs.getBoolean(IS_LOGGED_KEY, false);
    }

    @Override
    public void setIsLogged(boolean isLogged) {
        mPrefs.edit().putBoolean(IS_LOGGED_KEY, isLogged).apply();
    }
}
