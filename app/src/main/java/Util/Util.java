package Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.unas.app.R;

public class Util {


    public static final int[] ARRAY_ELEMENTS_ID = {R.array.ind_n22,
            R.array.ind_n20,
            R.array.ind_n19,
            R.array.ind_n18,
            R.array.ind_n15,
            R.array.ind_n14,
            R.array.ind_n11,
            R.array.ind_n10,
            R.array.ind_n9,
            R.array.ind_n8,
            R.array.ind_n7,
            R.array.ind_n6,
            R.array.ind_n5,
            R.array.ind_n4,
            R.array.ind_n3,
            R.array.ind_n2,
            R.array.ind_n1,
            R.array.ind_0,
            R.array.ind_1,
            R.array.ind_2,
            R.array.ind_3,
            R.array.ind_4,
            R.array.ind_5,
            R.array.ind_6,
            R.array.ind_7,
            R.array.ind_8,
            R.array.ind_9,
            R.array.ind_10,
            R.array.ind_11,
            R.array.ind_12,
            R.array.ind_13,
            R.array.ind_14,
            R.array.ind_15,
            R.array.ind_16,
            R.array.ind_17,
            R.array.ind_18,
            R.array.ind_19,
            R.array.ind_20,
            R.array.ind_21,
            R.array.ind_22,
            R.array.ind_23,
            R.array.ind_24,
            R.array.ind_25,
            R.array.ind_26};
    private static final String MyPREFERENCES = "MyPrefs";
    private static final String LAST_SCALE = "lastScale";

    public static int getScale(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getInt(LAST_SCALE, 0);
    }

    public static void setScale(Context context, int scale) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(LAST_SCALE, scale);
        editor.commit();
    }

    public static float scaleToZoomLevel(int scale) {
        //Start of available scales for in ARRAY_ELEMENTS_ID in Google Maps
        if (scale >= 17 && scale <= 24) {
            int[] zoomLevels = {23, 20, 17, 14, 10, 7, 3, 0};
            return zoomLevels[scale - 17];
        }
        return 0;
    }
}
