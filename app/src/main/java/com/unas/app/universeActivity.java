package com.unas.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class universeActivity extends Activity implements AnimationListener {

    int[] powersImage = {
            R.drawable.pot7,
            R.drawable.pot8,
            R.drawable.pot9,
            R.drawable.pot10,
            R.drawable.pot11
    };

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
        setContentView(R.layout.activity_universe);
        btZoomIn = (Button) (findViewById(R.id.btZoomIn));
        btZoomOut = (Button) (findViewById(R.id.btZoomOut));
        btInfo = (Button) (findViewById(R.id.btInfo));
        scale = 0;

        imageFront = (ImageView) (findViewById(R.id.first));
        imageBack = (ImageView) (findViewById(R.id.second));

        imageFront.setImageResource(powersImage[scale]);
    }

    public void onClickZoomIn(View v) {
        if (scale - 1 >= 0) {
            btZoomOut.setVisibility(View.VISIBLE);

            if (scale % 2 == 0) {
                imageFront = (ImageView) (findViewById(R.id.first));
                imageBack = (ImageView) (findViewById(R.id.second));
            } else {
                imageBack = (ImageView) (findViewById(R.id.first));
                imageFront = (ImageView) (findViewById(R.id.second));
            }

            imageFront.setImageResource(powersImage[scale]);
            imageBack.setImageResource(powersImage[scale - 1]);

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
    }

    public void onClickZoomOut(View v) {
        if (scale + 1 < powersImage.length) {
            btZoomIn.setVisibility(View.VISIBLE);

            if (scale % 2 == 0) {
                imageFront = (ImageView) (findViewById(R.id.first));
                imageBack = (ImageView) (findViewById(R.id.second));
            } else {
                imageBack = (ImageView) (findViewById(R.id.first));
                imageFront = (ImageView) (findViewById(R.id.second));
            }

            imageFront.setImageResource(powersImage[scale]);
            imageBack.setImageResource(powersImage[scale + 1]);

            scaleFront = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low_front);
            scaleFront.setAnimationListener(this);
            imageFront.startAnimation(scaleFront);

            scaleBack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low_back);
            scaleBack.setAnimationListener(this);
            imageBack.startAnimation(scaleBack);
            scale++;
            if (scale + 1 == powersImage.length)
                btZoomOut.setVisibility(View.INVISIBLE);
        }
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
