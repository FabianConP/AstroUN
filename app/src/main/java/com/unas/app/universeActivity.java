package com.unas.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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

    ScaleGestureDetector scaleGestureDetector;
    private Animation scaleFront;
    private Animation scaleBack;
    private ImageView imageFront;
    private ImageView imageBack;
    private int scale = 0;
    private Button btZoomIn;
    private Button btZoomOut;
    private Button btInfo;

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

        scale = Util.getScale(getApplicationContext());

        imageFront = (ImageView) (findViewById(R.id.first));
        imageBack = (ImageView) (findViewById(R.id.second));

        imageFront.setImageResource(Util.powersImage[scale]);
        imageBack.setImageResource(Util.powersImage[scale]);


        imageFront.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Context context = getApplicationContext();
                scaleGestureDetector = new
                        ScaleGestureDetector(context,
                        new MyOnScaleGestureListener(v));
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });

        imageBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Context context = getApplicationContext();
                scaleGestureDetector = new
                        ScaleGestureDetector(context,
                        new MyOnScaleGestureListener(v));
                scaleGestureDetector.onTouchEvent(event);
                return true;
            }
        });
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

    public class MyOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {

        private View view;

        public MyOnScaleGestureListener(View view) {
            this.view = view;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            float scaleFactor = detector.getScaleFactor();

            Log.w("myApp", scaleFactor + "");
            //Toast.makeText(getApplicationContext(),scaleFactor+"", Toast.LENGTH_SHORT).show();

            if (scaleFactor > 1) {
                onClickZoomOut(view);
                //Toast.makeText(getApplicationContext(),scaleFactor+" out", Toast.LENGTH_SHORT).show();

            } else {
                onClickZoomIn(view);
                //Toast.makeText(getApplicationContext(),scaleFactor+" in",Toast.LENGTH_SHORT).show();

            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }
}
