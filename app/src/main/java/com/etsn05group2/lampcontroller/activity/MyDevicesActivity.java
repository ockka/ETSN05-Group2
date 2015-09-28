package com.etsn05group2.lampcontroller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.etsn05group2.lampcontroller.R;
import com.etsn05group2.lampcontroller.adapter.DeviceListAdapter;
import com.etsn05group2.lampcontroller.model.Device;
import com.etsn05group2.lampcontroller.model.LightBulb;
import com.etsn05group2.lampcontroller.model.SensorDevice;
import com.etsn05group2.lampcontroller.network.NetworkManager;
import com.etsn05group2.lampcontroller.network.data.DataAboutDevice;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyDevicesActivity extends BaseActivity {
    private DeviceListAdapter listAdapter;
    List<Device> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_devices);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_devices, menu);
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

    private void detectDevices(){
        NetworkManager.detectDevices(createCallback());


    }

    private void controlDevice(Device device){

    }

    private Callback<List<DataAboutDevice>> createCallback(){
        Callback<List<DataAboutDevice>> call = new Callback<List<DataAboutDevice>>() {
            @Override
            public void success(List<DataAboutDevice> dataAboutDevices, Response response) {
                Log.d("hämtar lista","");
                saveList(dataAboutDevices);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("NÅGOT GICK VÄLDIGT FEL","");
                // popup med att det inte funkade att hitta listor

            }
        };
        return call;
    }
    private void saveList(List<DataAboutDevice> list){
        devices = new ArrayList<Device>();
        for(DataAboutDevice d: list){
            if(d.name == "Nexturn"){
                devices.add(new LightBulb(d.deviceAddress, d.id));
            }
            if(d.description == "WICED sense kit"){
                devices.add(new SensorDevice(d.deviceAddress, d.id));
            }
        }
        return;
    }
    public void startSensorDeviceActivity(View v){
        Intent intent = new Intent(this, SensorDeviceActivity.class);
        startActivity(intent);
    }
    public void startLighyBulbActivity(View v){
        Intent intent = new Intent(this, LightBulbActivity.class);
        startActivity(intent);
    }
}
