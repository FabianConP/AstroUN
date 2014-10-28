package com.unas.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


public class MainActivity extends Activity implements AnimationListener {

    private Animation animWelcome;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        animWelcome = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.welcome_animation);
        animWelcome.setAnimationListener(this);
        container = (FrameLayout) (findViewById(R.id.welcomeContainer));
        container.startAnimation(animWelcome);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for fade in animation
        if (animation == animWelcome) {
            //Toast.makeText(getApplicationContext(), "Animation Stopped",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), universeActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
