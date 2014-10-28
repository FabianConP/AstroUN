package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import Util.Util;


public class universeActivity extends Activity implements AnimationListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String LAST_SCALE = "lastScale";

    Animation scaleFront;
    Animation scaleBack;
    ImageView imageFront;
    ImageView imageBack;
    int scale = 0;
    Button btZoomIn;
    Button btZoomOut;
    Button btInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_universe);
        btZoomIn = (Button) (findViewById(R.id.btZoomIn));
        btZoomOut = (Button) (findViewById(R.id.btZoomOut));
        btInfo = (Button) (findViewById(R.id.btInfo));

        scale = getScale();

        imageFront = (ImageView) (findViewById(R.id.first));
        imageBack = (ImageView) (findViewById(R.id.second));

        imageFront.setImageResource(Util.powersImage[scale]);
        imageBack.setImageResource(Util.powersImage[scale]);
    }

    public void onClickZoomIn(View v) {
        if (scale - 1 >= 0) {
            btZoomOut.setVisibility(View.VISIBLE);

            imageFront.setImageResource(Util.powersImage[scale]);
            imageBack.setImageResource(Util.powersImage[scale - 1]);

            scaleBack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_in_front);
            scaleBack.setAnimationListener(this);
            imageFront.startAnimation(scaleBack);

            scaleBack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_in_back);
            scaleBack.setAnimationListener(this);
            imageBack.startAnimation(scaleBack);
            scale--;
            if (scale == 0)
                btZoomIn.setVisibility(View.INVISIBLE);
        }
        setScale(scale);
    }

    public void onClickZoomOut(View v) {
        if (scale + 1 < Util.powersImage.length) {
            btZoomIn.setVisibility(View.VISIBLE);

            imageFront.setImageResource(Util.powersImage[scale]);
            imageBack.setImageResource(Util.powersImage[scale + 1]);

            scaleFront = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low_front);
            scaleFront.setAnimationListener(this);
            imageFront.startAnimation(scaleFront);

            scaleBack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low_back);
            scaleBack.setAnimationListener(this);
            imageBack.startAnimation(scaleBack);
            scale++;
            if (scale + 1 == Util.powersImage.length)
                btZoomOut.setVisibility(View.INVISIBLE);
        }
        setScale(scale);
    }

    private int getScale() {
        SharedPreferences sharedPref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedPref.getInt(LAST_SCALE, 0);
    }

    private void setScale(int scale) {
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(LAST_SCALE, scale);
        editor.commit();
    }

    public void onClickInfo(View v) {
        Intent intent = new Intent(getApplication(), infoActivity.class);
        startActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        scale = getScale();
        imageFront.setImageResource(Util.powersImage[scale]);
        imageBack.setImageResource(Util.powersImage[scale]);

        if (scale == 0)
            btZoomIn.setVisibility(View.INVISIBLE);
        else
            btZoomIn.setVisibility(View.VISIBLE);

        if (scale + 1 == Util.powersImage.length)
            btZoomOut.setVisibility(View.INVISIBLE);
        else
            btZoomOut.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
