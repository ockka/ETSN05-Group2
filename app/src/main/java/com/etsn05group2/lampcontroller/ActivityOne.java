package com.etsn05group2.lampcontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class ActivityOne extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void startActivityTwo(View v) {
        Intent intent = new Intent(this, ActivityTwo.class);
       startActivity(intent);
    }

}
