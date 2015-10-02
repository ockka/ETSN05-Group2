package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.model.LightBulb;
import com.etsn05group2.lampcontroller.model.SensorDevice;

public abstract class DeviceActivity extends BaseActivity {
    protected Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String[] s = new String[3];
            s = extras.getStringArray("deviceInfo");
            if(s[0].equals("LightBulb")){

                device = new LightBulb(s[1], Long.valueOf(s[2]).longValue());

            }else if(s[0].equals("SensorDevice")){

                device = new SensorDevice(s[1], Long.valueOf(s[2]).longValue());

            }
        }

    }
/*
    protected void toggle(Boolean bool){
        //toggla via networkmanager
    }*/
}
