package com.unas.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class AboutUsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.about_us_activity);
    }

    public void onClickFacebookDev1(View view) {
        Uri uri = Uri.parse(getApplicationContext().getString(R.string.facebookDev1));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickTwitterDev1(View view) {
        Uri uri = Uri.parse(getApplicationContext().getString(R.string.twitterDev1));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickFacebookDev2(View view) {
        Uri uri = Uri.parse(getApplicationContext().getString(R.string.facebookDev2));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void onClickTwitterDev2(View view) {
        Uri uri = Uri.parse(getApplicationContext().getString(R.string.twitterDev2));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
