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

    public static final int[] ARRAY_ELEMENTS_DESC = {R.array.ind_n22_desc,
            R.array.ind_n20_desc,
            R.array.ind_n19_desc,
            R.array.ind_n18_desc,
            R.array.ind_n15_desc,
            R.array.ind_n14_desc,
            R.array.ind_n11_desc,
            R.array.ind_n10_desc,
            R.array.ind_n9_desc,
            R.array.ind_n8_desc,
            R.array.ind_n7_desc,
            R.array.ind_n6_desc,
            R.array.ind_n5_desc,
            R.array.ind_n4_desc,
            R.array.ind_n3_desc,
            R.array.ind_n2_desc,
            R.array.ind_n1_desc,
            R.array.ind_0_desc,
            R.array.ind_1_desc,
            R.array.ind_2_desc,
            R.array.ind_3_desc,
            R.array.ind_4_desc,
            R.array.ind_5_desc,
            R.array.ind_6_desc,
            R.array.ind_7_desc,
            R.array.ind_8_desc,
            R.array.ind_9_desc,
            R.array.ind_10_desc,
            R.array.ind_11_desc,
            R.array.ind_12_desc,
            R.array.ind_13_desc,
            R.array.ind_14_desc,
            R.array.ind_15_desc,
            R.array.ind_16_desc,
            R.array.ind_17_desc,
            R.array.ind_18_desc,
            R.array.ind_19_desc,
            R.array.ind_20_desc,
            R.array.ind_21_desc,
            R.array.ind_22_desc,
            R.array.ind_23_desc,
            R.array.ind_24_desc,
            R.array.ind_25_desc,
            R.array.ind_26_desc};
    private static final String MyPREFERENCES = "MyPrefs";
    private static final String LAST_SCALE = "lastScale";
    public static int[] powersImage = {
            R.drawable.potn22,
            R.drawable.potn20,
            R.drawable.potn19,
            R.drawable.potn18,
            R.drawable.potn15,
            R.drawable.potn14,
            R.drawable.potn11,
            R.drawable.potn10,
            R.drawable.potn9,
            R.drawable.potn8,
            R.drawable.potn7,
            R.drawable.potn6,
            R.drawable.potn5,
            R.drawable.potn4,
            R.drawable.potn3,
            R.drawable.potn2,
            R.drawable.potn1,
            R.drawable.pot0,
            R.drawable.pot1,
            R.drawable.pot2,
            R.drawable.pot3,
            R.drawable.pot4,
            R.drawable.pot5,
            R.drawable.pot6,
            R.drawable.pot7,
            R.drawable.pot8,
            R.drawable.pot9,
            R.drawable.pot10,
            R.drawable.pot11,
            R.drawable.pot12,
            R.drawable.pot13,
            R.drawable.pot14,
            R.drawable.pot15,
            R.drawable.pot16,
            R.drawable.pot17,
            R.drawable.pot18,
            R.drawable.pot19,
            R.drawable.pot20,
            R.drawable.pot21,
            R.drawable.pot22,
            R.drawable.pot23,
            R.drawable.pot24,
            R.drawable.pot25,
            R.drawable.pot26
    };

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
        if (scale >= 18 && scale <= 24) {
            int[] zoomLevels = {20, 17, 14, 10, 7, 3, 0};
            return zoomLevels[scale - 18];
        }
        return 0;
    }
}
