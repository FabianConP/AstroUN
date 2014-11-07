package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import Util.Util;


public class universeActivity extends Activity implements AnimationListener {

    private Animation scaleFront;
    private Animation scaleBack;
    private ImageView imageFront;
    private ImageView imageBack;
    private int scale = 0;
    private Button btZoomIn;
    private Button btZoomOut;
    private Button btInfo;

    private String[] ARRAY_SCALE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_universe);

        ARRAY_SCALE_NAME = getResources().getStringArray(R.array.scales);

        btZoomIn = (Button) (findViewById(R.id.btZoomIn));
        btZoomOut = (Button) (findViewById(R.id.btZoomOut));
        btInfo = (Button) (findViewById(R.id.btInfo));

        scale = Util.getScale(getApplicationContext());

        imageFront = (ImageView) (findViewById(R.id.first));
        imageBack = (ImageView) (findViewById(R.id.second));

        imageFront.setImageResource(Util.powersImage[scale]);
        imageBack.setImageResource(Util.powersImage[scale]);

        showToastScale(getApplicationContext());

        updateContentDescriptions();
    }

    private String getNameScale() {
        Context context = getApplicationContext();
        String pow = context.getString(R.string.pow);
        return "10 " + pow + " " + context.getResources().getStringArray(R.array.scales)[Util.getScale(context)];
    }

    private void showToastScale(Context context) {
        Toast toast = Toast.makeText(context, "10^" + ARRAY_SCALE_NAME[Util.getScale(context)], Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
        toast.show();
    }

    private void updateContentDescriptions() {
        imageFront.setContentDescription(getNameScale());
        imageBack.setContentDescription(getNameScale());
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
        Util.setScale(getApplicationContext(), scale);
        updateContentDescriptions();
        showToastScale(getApplicationContext());
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
        Util.setScale(getApplicationContext(), scale);
        updateContentDescriptions();
        showToastScale(getApplicationContext());
    }

    public void onClickInfo(View v) {
        Intent intent = new Intent(getApplication(), infoActivity.class);
        startActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        scale = Util.getScale(getApplicationContext());
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
