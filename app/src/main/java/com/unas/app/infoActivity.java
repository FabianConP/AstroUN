package com.unas.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class infoActivity extends Activity {

    public static int scale;

    private TextView tvValueRange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvValueRange = (TextView) findViewById(R.id.tvValueRange);

        Intent intent = getIntent();
        scale = intent.getIntExtra("SCALE",0);

        tvValueRange.setText(scale+"");

        Toast.makeText(getApplicationContext(),"Scale "+scale,Toast.LENGTH_SHORT).show();
    }

}
