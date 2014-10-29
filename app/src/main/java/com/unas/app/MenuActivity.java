package com.unas.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);
    }

    public void onClickTravel(View view) {
        Intent intent = new Intent(getApplicationContext(), universeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        String title = getApplicationContext().getString(R.string.closingApp);
        String question = getApplicationContext().getString(R.string.questionExitApp);
        String yes = getApplicationContext().getString(R.string.yesVar);
        String no = getApplicationContext().getString(R.string.noVar);
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(question)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(no, null)
                .show();
    }


}
