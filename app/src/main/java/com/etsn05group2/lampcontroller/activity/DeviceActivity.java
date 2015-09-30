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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void toggle(Boolean bool){
        //toggla via networkmanager
    }
}
