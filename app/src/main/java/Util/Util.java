package Util;

import android.content.Context;
import android.content.SharedPreferences;

public class Util {


    private static final String MyPREFERENCES = "MyPrefs";
    private static final String LAST_SCALE = "lastScale";

    public int getScale(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getInt(LAST_SCALE, 0);
    }

    public void setScale(Context context, int scale) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(LAST_SCALE, scale);
        editor.commit();
    }
}
