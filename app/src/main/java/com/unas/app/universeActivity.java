package com.unas.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class universeActivity extends Activity implements AnimationListener {

    int[] potenciasP = {
            R.drawable.pot7,
            R.drawable.pot8,
            R.drawable.pot9,
            R.drawable.pot10
    };

    Animation scaleLow;
    Animation scaleIn;
    Animation scaleUp;
    Animation scaleOut;
    ImageView imageOut;
    ImageView imageIn;
    int scale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        imageOut = (ImageView) (findViewById(R.id.first));
        imageIn = (ImageView) (findViewById(R.id.second));
        imageIn.setImageResource(potenciasP[scale + 1]);
        imageOut.setImageResource(potenciasP[scale]);


        scaleLow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low);
        scaleLow.setAnimationListener(this);
        imageOut.startAnimation(scaleLow);

        scaleIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_in2);
        scaleIn.setAnimationListener(this);
        imageIn.startAnimation(scaleIn);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == scaleIn && scale < 2) {
            scale++;
            if (scale % 2 == 0) {
                imageOut = (ImageView) (findViewById(R.id.first));
                imageIn = (ImageView) (findViewById(R.id.second));
            } else {
                imageIn = (ImageView) (findViewById(R.id.first));
                imageOut = (ImageView) (findViewById(R.id.second));
            }

            imageIn.setImageResource(potenciasP[scale + 1]);
            imageOut.setImageResource(potenciasP[scale]);

            scaleLow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_low);
            scaleLow.setAnimationListener(this);
            imageOut.startAnimation(scaleLow);

            scaleIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_in2);
            scaleIn.setAnimationListener(this);
            imageIn.startAnimation(scaleIn);
            /*scaleUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
			scaleUp.setAnimationListener(this);
	        imageIn = (ImageView)(findViewById(R.id.first));
	        imageIn.startAnimation(scaleUp);

	        scaleOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_out2);
	        scaleOut.setAnimationListener(this);
	        imageOut = (ImageView)(findViewById(R.id.second));
	        imageOut.startAnimation(scaleOut);*/
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }
}
